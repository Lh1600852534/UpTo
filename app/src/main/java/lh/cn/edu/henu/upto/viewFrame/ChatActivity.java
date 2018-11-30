package lh.cn.edu.henu.upto.viewFrame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.viewFrame.pie.PieData;
import lh.cn.edu.henu.upto.viewFrame.pie.PieView;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<PieData> pieDataArrayList = new ArrayList<>();
    private PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initPieData();
        pieView = findViewById(R.id.pie_view);
        pieView.setPieDataArrayList(pieDataArrayList);
    }

    /**
     * 初始饼状图数据
     */
    private void initPieData(){

        PieData pieData1 = new PieData("红",1);
        PieData pieData2 = new PieData("黄",2);
        PieData pieData3 = new PieData("蓝",8);
        PieData pieData4 = new PieData("绿",6);
        PieData pieData5 = new PieData("青",4);
        PieData pieData6 = new PieData("紫",2);
        pieDataArrayList.add(pieData1);
        pieDataArrayList.add(pieData2);
        pieDataArrayList.add(pieData3);
        pieDataArrayList.add(pieData4);
        pieDataArrayList.add(pieData5);
        pieDataArrayList.add(pieData6);
    }
}
