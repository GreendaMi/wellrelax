package UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.greendami.wellrelax.R;

import Listener.onSeekChangeLisner;

/**
 * Created by GreendaMi on 2017/1/17.
 */

public class MySeekBar extends ViewGroup {

    int active;//进度色


    int current = 0;//当前进度

    int widthSpecSize;
    int heightSpecSize;

    public Paint mPain = new Paint();

    public onSeekChangeLisner mOnSeekChangeLisner;
    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs , R.styleable.Seek);
        active = a.getColor(R.styleable.Seek_active, Color.WHITE);

        a.recycle();

        mPain.setColor(active);
        mPain.setAlpha(50);
        mPain.setAntiAlias(false);
        mPain.setStyle(Paint.Style.FILL);
    }

    public void setCurrent(int current) {
        this.current = current;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制活动区域
        canvas.drawRect(widthSpecSize/10,0,0.8f*widthSpecSize*current/100 + widthSpecSize/10,heightSpecSize,mPain);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Point now = new Point((int)(event.getX()),(int)(event.getY()));
        if(mOnSeekChangeLisner != null){
            //此处有触屏优化
            if(now.x <= widthSpecSize * 0.1f){
                mOnSeekChangeLisner.onChange(0);
                setCurrent(0);
            }else if(now.x >= widthSpecSize * 0.9f){
                mOnSeekChangeLisner.onChange(100);
                setCurrent(100);
            }else{
                mOnSeekChangeLisner.onChange(current);
                setCurrent((int)((now.x - widthSpecSize/10)/(0.8f * widthSpecSize)*100));
            }

        }
        MySeekBar.this.setScaleY(0.96f);
        MySeekBar.this.setScaleX(0.98f);
        return false;

    }
    public onSeekChangeLisner getOnSeekChangeLisner() {
        return mOnSeekChangeLisner;
    }

    public void setOnSeekChangeLisner(onSeekChangeLisner onSeekChangeLisner) {
        mOnSeekChangeLisner = onSeekChangeLisner;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

        }
        //这里直接设成来自父控件大小的一个正方形
        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    /**
     * 子View布局
     * @param b
     * @param i0
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    protected void onLayout(boolean b, int i0, int i1, int i2, int i3) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        LayoutParams cParams = null;
        int cl = 0, ct = 0, cr = 0, cb = 0;

        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();

            ct = (heightSpecSize - cHeight)/2;
            cl = (widthSpecSize -  cWidth)/2;
            cr = cWidth + cl;
            cb = cHeight + ct;

            childView.layout(cl , ct , cr, cb);
        }

    }

    /**
     * 通知父View，这个事件是不是你处理
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if(ev.getX() <=  widthSpecSize && ev.getY() <= heightSpecSize){
                    return super.dispatchTouchEvent(ev);
                }
                return false;
        }
        MySeekBar.this.setScaleX(1f);
        MySeekBar.this.setScaleY(1f);
        return true;
    }
}
