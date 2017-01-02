package UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.greendami.wellrelax.R;

import Listener.onSeekChangeLisner;

/**
 * Created by GreendaMi on 2016/11/25.
 */

public class Seek extends ViewGroup {

    private int active;//进度色


    private int current = 0;//当前进度

    int widthSpecSize;
    int heightSpecSize;

    int offset = 200;

    public Paint mPain = new Paint();

    public onSeekChangeLisner mOnSeekChangeLisner;

    public Seek(Context context) {
        super(context);
    }

    public Seek(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs , R.styleable.Seek);
        active = a.getColor(R.styleable.Seek_active, Color.WHITE);

        a.recycle();

        mPain.setColor(active);
        mPain.setAlpha(50);
        mPain.setAntiAlias(false);
        mPain.setStyle(Paint.Style.FILL);
    }

    public Seek(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        offset = (int)(Math.min(widthSpecSize,heightSpecSize) * 0.3);
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
     * 获取当前进度
     * @return
     */
    public int getCurrent() {
        return current;
    }

    /**
     * 设置进度，并请求重绘
     * @param current
     */
    public void setCurrent(int current) {
        if(current > 360)
            this.current = 360;
        else if(current < 0)
            this.current = current + 360 ;
        else this.current = current;
        if(this.current <= 180){
            invalidate();
        }
    }

    /**
     * 扇形的外矩形，与背景矩形的差
     * @return
     */
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        invalidate();
    }

    public onSeekChangeLisner getOnSeekChangeLisner() {
        return mOnSeekChangeLisner;
    }

    public void setOnSeekChangeLisner(onSeekChangeLisner onSeekChangeLisner) {
        mOnSeekChangeLisner = onSeekChangeLisner;
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制活动区域
        RectF rect = new RectF(-offset , -offset , widthSpecSize + offset , heightSpecSize + offset);
        canvas.drawArc(rect , 0 , -current , true , mPain);
        mPain.setAlpha(100);
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
        Seek.this.setScaleX(1f);
        Seek.this.setScaleY(1f);
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Point center = new Point(widthSpecSize / 2, heightSpecSize / 2);
        Point now = new Point((int)(event.getX()),(int)(event.getY()));
        int x=(now.x  - center.x );
        int y=(center.y - now.y);
        setCurrent((int)(Math.toDegrees(Math.atan2(y , x))));
        if(mOnSeekChangeLisner != null){
            //此处有触屏优化
            if(current > 300){
                mOnSeekChangeLisner.onChange(0);
                current = 0;
            }else if(current > 180){
                mOnSeekChangeLisner.onChange(100);
                current = 180;
            }else{
                mOnSeekChangeLisner.onChange((int)(current/1.8));
            }

        }

        Seek.this.setScaleX(0.95f);
        Seek.this.setScaleY(0.95f);
        return false;

    }



}

