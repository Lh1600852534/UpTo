package lh.cn.edu.henu.upto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import lh.cn.edu.henu.upto.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zeklles", "onResume:Main1Activity:  " + Thread.currentThread().getName());
    }
}
