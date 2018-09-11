package lh.cn.edu.henu.upto.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import javax.security.auth.login.LoginException;

import lh.cn.edu.henu.upto.R;

public class SlideLayout extends FrameLayout {

    private View mContentView;
    private View mMenuView;

    private int mMenuWidth;
    private int mMenuHeight;
    private int mContentWidth;
    private int mContentHeight;

    private Scroller mScroller;

    private float startX;
    private float startY;
    private float downX;
    private float downY;

    private float disX;

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mMenuView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentWidth = getMeasuredWidth();
        mContentHeight = getMeasuredHeight();
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMenuHeight = mMenuView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mMenuView.layout(mContentWidth, 0, mContentWidth + mMenuWidth, mMenuHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         float x = event.getX();
         float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                float dx = (int)(startX - x);
                disX = dx + disX;

                if(disX <= 0){
                    disX = 0;
                }else if(disX > mMenuWidth){
                    disX = mMenuWidth;
                }

                scrollTo((int)disX, 0);

                final float moveX = Math.abs(x - startY);
                final float moveY = Math.abs(y - startY);
                if(moveX > moveY && moveX > 10f){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                if(disX < mMenuWidth / 2){
                    closeMenu();
                    disX = 0;
                }else {
                    openMenu();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;
        final float x = ev.getX();
        final float y = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                final float moveX = Math.abs(x - downX);
                if(moveX > 10f){
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return intercept;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public final void openMenu(){
        mScroller.startScroll(getScrollX(), getScrollY(), mMenuWidth - getScrollX(), 0);
        invalidate();
    }

    public final void closeMenu(){
        mScroller.startScroll(getScrollX(), getScrollY(), 0 - getScrollX(), 0);
        invalidate();
    }

    /**
     * 将item复位
     */
    public void resetMenu(){

        scrollTo(0, 0);
    }




}
