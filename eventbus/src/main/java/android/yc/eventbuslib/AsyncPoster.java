package android.yc.eventbuslib;

/**
 * Created by lingjiu on 2019/1/12.
 */
public class AsyncPoster implements Runnable {
    private final PendingPostQueue queue;
    private final EventBus eventBus;

    AsyncPoster(EventBus eventBus) {
        this.eventBus = eventBus;
        queue = new PendingPostQueue();
    }

    public void enqueue(Subscription subscription, Object event) {
        PendingPost pendingPost = PendingPost.obtainPendingPost(subscription, event);
        queue.enqueue(pendingPost);
        eventBus.getExecutorService().execute(this);
    }

    @Override
    public void run() {
        PendingPost pendingPost = queue.poll();
        if (pendingPost == null) {
            throw new IllegalStateException("No pending post available");
        }
        eventBus.invokeMethod(pendingPost);
    }
}
