package business;

/**
 * Created by GreendaMi on 2016/11/26.
 */

public class DecSecond {
    public static String DecOneSecond(String mTime) {
        int h = Integer.parseInt(mTime.split(":")[0]);
        int m = Integer.parseInt(mTime.split(":")[1]);
        int s = Integer.parseInt(mTime.split(":")[2]);
        if(s == 0){
            s = 59;
            m = m - 1;
        }else {
            s = s - 1;
        }

        if(m < 0){
            m = 23;
            h = h - 1;
        }

        String result = "";
        if(m < 10){
            result = "0" + "" + h + ":0" + m;
        }else{
            result = "0" + "" + h + ":" + m;
        }

        if(s < 10){
            result = result + ":0" + s;
        }else{
            result = result + ":" + s;
        }
        return result;
    }
}
