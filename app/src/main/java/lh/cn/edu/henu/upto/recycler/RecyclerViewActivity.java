package lh.cn.edu.henu.upto.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

import lh.cn.edu.henu.upto.R;

public class RecyclerViewActivity extends AppCompatActivity implements SlideLayoutAdapter.SlideLayoutClickListener {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_slide);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        LinkedList<String> arrayList = new LinkedList<>();
        for(int i = 0; i < 20; i++){
            arrayList.add(i + "");
        }
        SlideLayoutAdapter slideLayoutAdapter = new SlideLayoutAdapter(this, arrayList);
        slideLayoutAdapter.setSlideLayoutClickListener(this);
        recyclerView.setAdapter(slideLayoutAdapter);
    }

    @Override
    public void onRefresh(int position, SlideLayout view, int type) {

        switch (type){
            case 1:
                Log.i("tttss", "onRefresh: dasgsgas");
                recyclerView.smoothScrollToPosition(0);
                break;

        }
    }
}
