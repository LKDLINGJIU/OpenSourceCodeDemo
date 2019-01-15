package android.yc.eventbuslib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要接受message的注解
 * Created by lingjiu on 2019/1/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    //线程模式
    ThreadMode threadMode() default ThreadMode.POSTING;

    //是否是粘性消息(可往下面传递)
    boolean sticky() default false;

    //优先级
    int priority() default 0;
}
