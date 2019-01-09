package android.yc.eventbuslib;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 仿写Eventbus两个普通类之间的通信
 * 1注册,反注册
 * 获取注册类经过subscribe注解的方法
 * 将其保存
 * 2在发送消息的时候postMessage
 * 将注册过的类方法比较,是否符合规则,
 * 如果符合规则则将其执行
 * 3线程问题如何处理
 * <p>
 * <p>
 * Created by lingjiu on 2019/1/9.
 */
public class EventBus implements EventBusInterface {

    private EventBus() {
        mSubscribeEntities = new ArrayList<>();
        stickyMessageList = new ArrayList<>();
    }

    private static EventBus instance = new EventBus();

    public static EventBus getDefault() {
        return instance;
    }


    //所有已注册的对象以及被注解的方法
    private List<SubscribeEntity> mSubscribeEntities;

    //sticky消息存放的集合
    private List<Object> stickyMessageList;

    //已删除的消息
    private Object deleteMessage;

    @Override
    public void postMessage(Object message) {
        try {
            //遍历所有注册的类(已根据优先级排序过)
            for (int i = 0; i < mSubscribeEntities.size(); i++) {
                if (deleteMessage != null && deleteMessage.equals(message)) {
                    Log.i("yc", "消息已被取消");
                    deleteMessage = null;
                    return;
                }
                SubscribeEntity subscribeEntity = mSubscribeEntities.get(i);
                Method method = subscribeEntity.method;
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes[0] == message.getClass() || parameterTypes[0] == Object.class) {
                    method.invoke(subscribeEntity.object, message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postStickyMessage(Object message) {
        stickyMessageList.add(message);
    }

    @Override
    public void cancelEventDelivery(Object message) {
        deleteMessage = message;
        for (int i = 0; i < stickyMessageList.size(); i++) {
            if (stickyMessageList.get(i).equals(message)) {
                stickyMessageList.remove(i);
                break;
            }
        }
    }

    @Override
    public void register(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            //只添加有一个参数且被注解的方法
            if (method.isAnnotationPresent(Subscribe.class)) {
                Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                //添加到集合，并根据优先级排序
                SubscribeEntity subscribeEntity = new SubscribeEntity(object, method, subscribeAnnotation.priority(), subscribeAnnotation.threadMode());
                Utils.addAndSort(mSubscribeEntities,
                        subscribeEntity);
                //判断是否有sticky消息要执行
                executorStickyMessage(subscribeEntity);
            }
        }

    }

    private void executorStickyMessage(SubscribeEntity subscribeEntity) {
        try {
            for (Object message : stickyMessageList) {
                Method method = subscribeEntity.method;
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (message.getClass() == parameterTypes[0] ||
                        parameterTypes[0] == Object.class) {
                    method.invoke(subscribeEntity.object, message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unRegister(Object object) {
        Iterator<SubscribeEntity> iterator = mSubscribeEntities.iterator();
        while (iterator.hasNext()) {
            SubscribeEntity next = iterator.next();
            if (next.isSameObject(object)) {
                iterator.remove();
            }
        }
    }
}
