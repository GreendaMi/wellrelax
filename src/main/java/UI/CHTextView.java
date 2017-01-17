package UI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by GreendaMi on 2017/1/17.
 */

public class CHTextView extends TextView {
    public CHTextView(Context context) {
        super(context);
    }

    public CHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "iconfont/CH.ttf");
        setTypeface(typeface);
    }


}