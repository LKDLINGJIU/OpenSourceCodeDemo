package android.yc.eventbuslib;

/**
 * Created by lingjiu on 2019/1/9.
 */
public interface EventBusInterface {

    void postMessage(Object message);

    void postStickyMessage(Object message);

    void cancelEventDelivery(Object message);

    void register(Object object);

    void unRegister(Object object);


}
