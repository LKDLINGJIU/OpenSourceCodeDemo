package android.yc.com.open.sourcecode.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.yc.eventbuslib.EventBus;
import android.yc.eventbuslib.Subscribe;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        EventBus.getDefault().register(this);
    }


    @Subscribe(priority = 1)
    public void onMessageEvent(String message) {
        Log.i("yc", "ThirdActivity  消息===" + message + "    priority 1");
    }
}
