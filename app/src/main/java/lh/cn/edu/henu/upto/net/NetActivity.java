package lh.cn.edu.henu.upto.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lh.cn.edu.henu.upto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnJinSanCiBa, btnWangYiYouDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initView();
    }

    public void initView(){
        btnJinSanCiBa = (Button)findViewById(R.id.retrofit_jin_san_ci_ba);
        btnJinSanCiBa.setOnClickListener(this);
        btnWangYiYouDao = (Button)findViewById(R.id.retrofit_wang_yi_you_dao);
        btnWangYiYouDao.setOnClickListener(this);
    }

    public void request(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //@GET("ajax.php?a=fy&f=auto&t=auto&w=你好，世界。我想加入金山词霸")
       // Call<Translation> call = request.getCall("fy","auto","auto","你好，世界。我想加入金山词霸");

        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.retrofit_jin_san_ci_ba:
                request();
                break;
            case R.id.retrofit_wang_yi_you_dao:
                break;
        }
    }
}
