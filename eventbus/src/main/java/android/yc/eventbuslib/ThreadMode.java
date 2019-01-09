package android.yc.eventbuslib;

/**
 * Created by lingjiu on 2019/1/9.
 */
public interface ThreadMode {
    String POSTING = "POSTING";//默认的Called in the same thread (default)，在同一个线程中执行。

    //主线程
    String MAIN = "MAIN";//Called in Android UI's main thread


    String MAIN_ORDERED = "MAIN_ORDERED";// Called in Android UI's main thread ，不过需要排队，如果前一个也是main_ordered
    // 需要等前一个执行完成后才执行，在主线程中执行，可以处理更新ui的操作。

    //后台线程
    String BACKGROUND = "BACKGROUND";//Called in the background thread，后台进程，处理如保存到数据库等操作。

    //异步线程
    String ASYNC = "ASYNC";//Called in a separate thread 异步执行，另起线程操作。

}
