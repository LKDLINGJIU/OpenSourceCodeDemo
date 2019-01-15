package android.yc.eventbuslib;

import android.support.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * Created by lingjiu on 2019/1/9.
 */
public class Subscription implements Comparable {

    //注册的类
    public Object object;
    //被注解的方法
    public Method method;
    public int priority;
    public ThreadMode threadMode;

    public Subscription(Object object, Method method, int priority, ThreadMode threadMode) {
        this.object = object;
        this.method = method;
        this.priority = priority;
        this.threadMode = threadMode;
    }



    /***
     * 判断是同一个object
     * @param object
     * @return
     */
    public boolean isSameObject(Object object) {
        return this.object == object;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        if (!(o instanceof Subscription))
            return -1;
        return priority - ((Subscription) o).priority;
    }
}
