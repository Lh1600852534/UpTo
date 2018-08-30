package lh.cn.edu.henu.upto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.adapter.RecyclerTestAdapter;
import lh.cn.edu.henu.upto.bean.Fruit;

public class RecyclerActivity extends AppCompatActivity {

    ArrayList<Fruit> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initFruit();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.test_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerTestAdapter recyclerTestAdapter = new RecyclerTestAdapter(lists);
        recyclerView.setAdapter(recyclerTestAdapter);
    }

    void initFruit(){

        for(int i = 0; i <= 4; i++){
            Fruit fruit1 = new Fruit("apple", 0);
            lists.add(fruit1);
            Fruit fruit2 = new Fruit("banana",1);
            lists.add(fruit2);
            Fruit fruit3 = new Fruit("orange", 2);
            lists.add(fruit3);
            Fruit fruit4 = new Fruit("pear",3);
            lists.add(fruit4);

        }
    }
}
