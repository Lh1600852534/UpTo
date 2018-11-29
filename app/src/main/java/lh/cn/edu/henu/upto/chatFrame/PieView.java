package lh.cn.edu.henu.upto.chatFrame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class PieView extends View {

    //颜色集合 16种颜色
    private static ArrayList<Integer> colors;
    private ArrayList<PieData> pieDataArrayList;

    private float arcLeft, arcTop, arcRight, arcBottom;
    private float mLeft, mTop, mRight, mBottom;

    private Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initColors();
        initPaint();
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置初始数据
     * @param pieDataArrayList 数据集合
     */
    public void setPieDataArrayList(ArrayList<PieData> pieDataArrayList){
        this.pieDataArrayList = pieDataArrayList;
        invalidate();
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mLeft = getLeft();
        mTop = getTop();
        mRight = getRight();
        mBottom = getBottom();
        initLayout();
    }

    //确定圆弧上下左右位置
    private void initLayout(){
        float mVertical = mBottom - mTop;
        float mHorizontal = mRight - mLeft;
        float dif = mVertical - mHorizontal;
        if(dif > 0){
            arcTop = mTop + (dif / 2);
            arcBottom = mBottom - (dif / 2);
            arcLeft = mLeft;
            arcRight = mRight;
        }else if(dif < 0){
            arcTop = mTop;
            arcBottom = mBottom;
            arcLeft = mLeft - (dif / 2);
            arcRight = mRight + (dif / 2);
        }else {
            arcTop = mTop;
            arcBottom = mBottom;
            arcLeft = mLeft;
            arcRight = mRight;
        }
    }

    //颜色集合初始化
    private void initColors(){
        int[] arrayColor = {0Xff880015,0Xffb97a57,0Xffed1c24,0Xffffaec9,
                0Xffff7f27,0Xffffc90e,0Xfffff200,0Xffefe4b0,0Xff22b14c,0Xffb5e61d,
                0Xff00a2e8,0Xff99d9ea,0Xff3f48cc,0Xff7092be,0Xffa349a4,0Xffc8bfe7
        };
        colors = new ArrayList<Integer>();
        for (Integer i : arrayColor) {
            colors.add(i);
        }
    }

    /*
    * 重构颜色集合
    * @param num 需要添加颜色种数
    * */
    private void refactorColors(int num){

        int rgb;
        while (num > 0){

            rgb = generateColor();
            if(!colors.contains(rgb)){
                colors.add(rgb);
                num--;
            }
        }
    }

    //随机生成颜色
    private int generateColor(){
        Random random = new Random();
        String r,g,b;
        r = Integer.toHexString(random.nextInt(256));
        g = Integer.toHexString(random.nextInt(256));
        b = Integer.toHexString(random.nextInt(256));

        return Integer.parseInt("0Xff" + r + g + b);
    }


    //对每个PieData进行分配颜色 计算百分比 弧度
    private void updatePieDate(ArrayList<PieData> pieDataArrayList){

        //判断颜色集合是否够用
        if(pieDataArrayList.size() > colors.size()){
            //向颜色集合中添加颜色
            refactorColors(pieDataArrayList.size() - colors.size());
        }

        double totalValue = 0;

        for (PieData pieData : pieDataArrayList) {

            totalValue += pieData.getValue();
        }

        for(int i = 0; i < pieDataArrayList.size(); i++){
            PieData pieData = pieDataArrayList.get(i);
            pieData.setColor(i);
            pieData.setPercentage((pieData.getValue() / totalValue) * 100);
            pieData.setAngle(Float.parseFloat((pieData.getValue() / totalValue) * 360 +""));
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("pieView", "onDraw1: ");
        float currentAngle = 0; //当前弧度
        if(pieDataArrayList == null) return;
        updatePieDate(pieDataArrayList);
        initPaint();

        Log.i("pieView", "onDraw2:  list.size" + pieDataArrayList.size());
        for (PieData pieData : pieDataArrayList) {

            Log.i("pieView", "onDraw3: " + pieData.getName() + " " + pieData.getAngle());
            mPaint.setColor(colors.get(pieData.getColor()));
            RectF rectF = new RectF(arcLeft, arcTop, arcRight, arcBottom);
            canvas.drawArc(rectF, currentAngle, pieData.getAngle(), true, mPaint);
            currentAngle += pieData.getAngle();
        }

/*        mPaint.setColor(0XFF880015);
        RectF rectF = new RectF(arcLeft, arcTop, arcRight, arcBottom);
        canvas.drawArc(rectF, currentAngle, 90, true, mPaint);*/


    }
}
