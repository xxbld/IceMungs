package org.github.xxbld.icemung.webservices;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：服务端数据返回基本类型
 */
public class BaseResult<T> {

//  event为返回码，0表示成功,
//  msg则是返回的信息，
//  obj是返回的数据对象,
//  currentPage表示当前页，
//  pageSize则表示当前页最多对象数量，
//  maxCount表示对象数据总量，
//  maxPage表示总共有多少页。

    private String event;
    private String msg;
    private T obj;
    private int currentPage;
    private int pageSize;
    private int maxCount;
    private int maxPage;

    public BaseResult() {
    }


    @Override
    public String toString() {
        return "BaseResult{" +
                "event='" + event + '\'' +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", maxCount=" + maxCount +
                ", maxPage=" + maxPage +
                '}';
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
