package business;

import android.content.Context;

import com.greendami.wellrelax.R;

/**
 * Created by GreendaMi on 2016/11/26.
 */

public class TimeStringConVert {
    public static String getTimeString(Context context , String time){
        if(!time.contains(":"))
            return time;

        char[] s = time.toCharArray();
        String result = "";
        for (char c : s) {
            if(c == '0'){
                result = result + context.getResources().getString(R.string.ling);
            }
            if(c == '1'){
                result = result + context.getResources().getString(R.string.yi);
            }
            if(c == '2'){
                result = result + context.getResources().getString(R.string.er);
            }
            if(c == '3'){
                result = result + context.getResources().getString(R.string.san);
            }
            if(c == '4'){
                result = result + context.getResources().getString(R.string.si);
            }
            if(c == '5'){
                result = result + context.getResources().getString(R.string.wu);
            }
            if(c == '6'){
                result = result + context.getResources().getString(R.string.liu);
            }
            if(c == '7'){
                result = result + context.getResources().getString(R.string.qi);
            }
            if(c == '8'){
                result = result + context.getResources().getString(R.string.ba);
            }
            if(c == '9'){
                result = result + context.getResources().getString(R.string.jiu);
            }
            if(c == ':'){
                result = result + context.getResources().getString(R.string.maohao);
            }
        }
        return result;
    }
}
