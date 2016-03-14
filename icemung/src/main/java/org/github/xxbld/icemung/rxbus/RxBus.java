package org.github.xxbld.icemung.rxbus;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by xxbld on 2016/1/9
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class RxBus {

    private static volatile RxBus mInstance = null;
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    private RxBus() {
    }

    /**
     * get RxBus instance
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }


    /**
     * send event
     *
     * @param o object class
     */
    public void send(Object o) {
        _bus.onNext(o);
    }

    /**
     * receiver event
     * <p/>
     * toObservable().subscribe(new Action1<Object>() {
     *
     * @Override public void call(Object o) {
     * if (o instanceof XXXEvent){
     * //do some th
     * }
     * }
     * });
     * </p>
     */
    public Observable<Object> toObservable() {
        return _bus;
    }

    /**
     * receiver event
     * @param eventType a.class
     * @param <T>
     * @return
     */
    public <T extends Object> Observable<T> toObservable(final Class<T> eventType) {
        return _bus.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return eventType.isInstance(o);
            }
        }).cast(eventType);
    }

    /**
     * 取消订阅事件
     * @param subscribe
     */
    public void unSubscribe(Subscription subscribe){
        if (!subscribe.isUnsubscribed()){
            subscribe.unsubscribe();
        }
    }
}
