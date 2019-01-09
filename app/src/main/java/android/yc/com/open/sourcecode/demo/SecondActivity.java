package android.yc.com.open.sourcecode.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.yc.eventbuslib.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getDefault().register(this);
    }

    public void sendMsg(View view) {
        EventBus.getDefault().postMessage("消息来自于=================>" + getClass().getSimpleName());
    }

    public void goThirdActivity(View view) {
        EventBus.getDefault().postStickyMessage("sticky 消息来自于=================>" + getClass().getSimpleName());
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
