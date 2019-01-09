package android.yc.com.open.sourcecode.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.yc.eventbuslib.EventBus;
import android.yc.eventbuslib.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(priority = 1)
    public void onMessageEvent(String message) {
        Log.i("yc", "MainActivity  消息===" + message + "    priority 1");
    }

    @Subscribe(priority = 2)
    public void onMessageEvent(Object message) {
        Log.i("yc", "MainActivity  消息===" + message + "    priority 2");
        EventBus.getDefault().cancelEventDelivery(message);
    }

    public void toSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
