package lh.cn.edu.henu.upto.HandlerThread;

import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lh.cn.edu.henu.upto.R;

public class HandlerThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        HandlerThread handlerThread = new HandlerThread("ttss");
        handlerThread.start();
    }
}
