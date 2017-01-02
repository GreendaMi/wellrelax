package UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.greendami.wellrelax.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 彩虹条控件
 * Created by GreendaMi on 17/1/1.
 */

public class Rainbow extends ViewGroup {

    Map mList = new HashMap();
    Context mContext;

    int widthSpecSize;
    int heightSpecSize;

    public Paint mPain = new Paint();

    public Rainbow(Context context) {
        super(context);
    }

    public Rainbow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

    public Rainbow(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPain.setAntiAlias(false);
        mPain.setStyle(Paint.Style.FILL);
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
        Log.d("Rainbow", "widthSpecSize:" + widthSpecSize);
        Log.d("Rainbow", "heightSpecSize:" + heightSpecSize);
        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("Rainbow", "onLayout");
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

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Rainbow", "onDraw");
        super.onDraw(canvas);
        int total = 0;
        for (Object in : mList.keySet()) {
           total = total + (int)(mList.get(in));
        }
        int startw = 0;
        int endw = 0;
        for (Object in : mList.keySet()) {
            setPaintColor(in);
            int mW = (int)(mList.get(in)) * widthSpecSize / total ;

            endw = endw + mW;
            canvas.drawRect(startw,0,endw,heightSpecSize,mPain);
            startw = startw + mW;
        }
    }

    private void setPaintColor(Object in) {
        switch ((int) in){
            case 1:
                mPain.setColor(mContext.getResources().getColor(R.color.one));
                break;
            case 2:
                mPain.setColor(mContext.getResources().getColor(R.color.two));
                break;
            case 3:
                mPain.setColor(mContext.getResources().getColor(R.color.three));
                break;
            case 4:
                mPain.setColor(mContext.getResources().getColor(R.color.four));
                break;
            case 5:
                mPain.setColor(mContext.getResources().getColor(R.color.five));
                break;
            case 6:
                mPain.setColor(mContext.getResources().getColor(R.color.six));
                break;
            case 7:
                mPain.setColor(mContext.getResources().getColor(R.color.seven));
                break;
            case 8:
                mPain.setColor(mContext.getResources().getColor(R.color.eight));
                break;
            case 9:
                mPain.setColor(mContext.getResources().getColor(R.color.nine));
                break;
            case 10:
                mPain.setColor(mContext.getResources().getColor(R.color.ten));
                break;
            case 11:
                mPain.setColor(mContext.getResources().getColor(R.color.eleven));
                break;
            case 12:
                mPain.setColor(mContext.getResources().getColor(R.color.twelve));
                break;
            case 13:
                mPain.setColor(mContext.getResources().getColor(R.color.thirteen));
                break;
            case 14:
                mPain.setColor(mContext.getResources().getColor(R.color.fourteen));
                break;

        }
    }


    public void setmList(Map mList) {
        this.mList = mList;
        invalidate();
    }


}
