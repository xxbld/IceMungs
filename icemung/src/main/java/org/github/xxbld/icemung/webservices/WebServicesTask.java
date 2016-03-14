package org.github.xxbld.icemung.webservices;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xxbld on 2016/1/13.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :AsyncTask To execute Webservice
 */
public class WebServicesTask extends AsyncTask<WebServicesParameter, Integer, WebServicesResult> {
    private final String TAG = WebServicesTask.class.getSimpleName();
    private WebServicesCallback callback;
    private WebServicesResult webServicesResult = null;

    public WebServicesTask(WebServicesCallback callback) {
        this.callback = callback;
    }

    /**
     * not use
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    private String doItJsonStr(WebServicesParameter parameter) throws Exception {
        WebServicesResult webServicesResult = doIt(parameter);
        Gson gson = new Gson();
        return gson.toJson(webServicesResult);
    }


    /**
     * 链接 web services
     *
     * @param parameter
     * @return WebServicesResult
     * @throws Exception
     */
    private WebServicesResult doIt(WebServicesParameter parameter) throws Exception {
        parameter.soapAction = parameter.nameSpace + parameter.methodName;
        Log.i(TAG, parameter.toString());
        // 实例化SoapObject对象，传入所要调用的Web Service的命名空间，WebService方法名
        SoapObject soapObject = new SoapObject(parameter.nameSpace, parameter.methodName);
        if (parameter.paraMap != null && parameter.paraMap.size() > 0) {
            for (Map.Entry<String, String> entry : parameter.paraMap.entrySet()) {
                // 添加差数（参数名，参数值）
                soapObject.addProperty(entry.getKey(), entry.getValue());
                Log.i(TAG, "key:" + entry.getKey() + "value:" + entry.getValue());
            }
        }
        // 采用SOAP1.1协议——VER11
        SoapSerializationEnvelope envelop = new SoapSerializationEnvelope(
                parameter.soapVersion);
        // 将 soapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
        envelop.bodyOut = soapObject;
        envelop.dotNet = parameter.dotNet;
        envelop.setOutputSoapObject(soapObject);

        HttpTransportSE trans = new HttpTransportSE(parameter.webserviceUrl);
        trans.debug = true;

        webServicesResult = new WebServicesResult();
        try {
            // 开始调用webservice
            Log.i(TAG, "开始调用webservice!!" + "action:" + parameter.soapAction);
            trans.call(parameter.soapAction, envelop);
            if (envelop.getResponse() != null) {
                String result1 = envelop.getResponse().toString();
                webServicesResult.result = result1;
                if (result1.equals("anyType{}")) {
                    Log.i(TAG, "json null!!!");
                    webServicesResult.isNull = true;
                    webServicesResult.result = null;
                }
                Log.i(TAG, "getResponse:" + result1);
            } else {
                webServicesResult.isConnectError = true;
                Log.i(TAG, "getResponse:  无响应！！！！");
            }
        } catch (IOException e1) {
            webServicesResult.exceptionMsg = e1.getMessage();
            e1.printStackTrace();
        }
        return webServicesResult;
    }

    @Override
    protected WebServicesResult doInBackground(WebServicesParameter... params) {
        WebServicesParameter parameters = params[0];
        try {
            return doIt(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        if (callback != null) {
            callback.onPre();
        }
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(WebServicesResult result) {
        if (result == null) {
            return;
        }
        if (callback != null) {
            if (result.isConnectError) {
                callback.onConnectFailed();
            } else if (result.exceptionMsg != null) {
                callback.onError(result.exceptionMsg);
            } else {
                callback.onResultFull(result.result);
            }
        }
    }

}