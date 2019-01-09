package android.yc.eventbuslib;

import android.support.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * Created by lingjiu on 2019/1/9.
 */
public class SubscribeEntity implements Comparable {

    //注册的类
    public Object object;
    //被注解的方法
    public Method method;
    public int priority;
    public String threadMode;

    public SubscribeEntity(Object object, Method method, int priority, String threadMode) {
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
        if (!(o instanceof SubscribeEntity))
            return -1;
        return priority - ((SubscribeEntity) o).priority;
    }
}
