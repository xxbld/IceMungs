package org.github.xxbld.icemung.webservices;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxbld on 2016/1/13.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :necessary parameter of Webservice
 * <p>
 * WebServicesParameter parameter = new WebServicesParameter();
 * parameter.nameSpace = "http://XXX.edu.cn/";
 * parameter.methodName = "Login";
 * parameter.webserviceUrl = "http://XXX.edu.cn/travel/travelbll.asmx";
 * String[] login_keys = "cardID|pwd".split("\\|");
 * String[] login_values = {"9019810001", "123"};
 * parameter.setKeysAndValues(login_keys, login_values);
 * </p>
 */
public class WebServicesParameter {

    /**
     * NameSpace
     */
    public String nameSpace;
    /**
     * Soap Action |nameSpace + methodName
     */
    public String soapAction;
    /**
     * MethodName
     */
    public String methodName;
    /**
     * Webservice Url
     */
    public String webserviceUrl;
    /**
     * is a dotNet webservice ; default true
     */
    public boolean dotNet = true;

    /**
     * soap version |SoapEnvelope.VER11、VER10、VER12
     */
    public int soapVersion = SoapEnvelope.VER11;

    /**
     * map for fieldName and target value
     */
    public Map<String, String> paraMap = new HashMap<>();

    public WebServicesParameter() {
    }

    /**
     * set keys values by array
     *
     * @param _keys
     * @param _values
     */
    public void setKeysAndValues(String[] _keys, String[] _values) {
        clearKeyValues();
        if (_keys.length != _values.length) {
            throw new IllegalArgumentException("keys size must equal values size!!");
        }
        for (int i = 0; i < _keys.length; i++) {
            this.paraMap.put(_keys[i], _values[i]);
        }
    }

    public void setKeysAndValues(List<String> _keys, List<String> _values) {
        clearKeyValues();
        if (_keys.size() != _values.size()) {
            throw new IllegalArgumentException("keys size must equal values size!!");
        }
        for (int i = 0; i < _keys.size(); i++) {
            this.paraMap.put(_keys.get(i), _values.get(i));
        }

    }


    /**
     * clear maps
     */
    private void clearKeyValues() {
        if (this.paraMap.isEmpty()) {
            return;
        } else {
            this.paraMap.clear();
        }
    }
}
