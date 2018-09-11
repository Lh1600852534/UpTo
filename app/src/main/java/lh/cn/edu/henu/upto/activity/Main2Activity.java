package lh.cn.edu.henu.upto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import lh.cn.edu.henu.upto.R;

public class Main2Activity extends AppCompatActivity {

    private Button btn_show, btn_hide;
    private ViewStub viewStub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }

    private void initView() {
        viewStub = (ViewStub)findViewById(R.id.stub_view);

        btn_show = (Button)findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.inflate();
                btn_show.setClickable(false);
            }
        });
        btn_hide = (Button)findViewById(R.id.btn_hide);
        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zeklles", "onResume:Main1Activity:  " + Thread.currentThread().getName());
    }
}
