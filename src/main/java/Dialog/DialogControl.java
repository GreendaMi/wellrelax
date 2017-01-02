package Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.greendami.wellrelax.Main;
import com.greendami.wellrelax.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BaseAdapter;
import Adapter.ViewHolder;
import UI.IconFontTextView;
import business.TimeStringConVert;
import business.Timer.TickTimer;

/**
 * Created by GreendaMi on 2016/11/26.
 */

public class DialogControl {

    AlertDialog mDialog;
    Context mContext;
    List<String> mDatas ;
    List<String> mShowTimes ;
    IconFontTextView mIconFontTextView;
    TickTimer timer;
    Handler mHandler;

    public void initDialog(Context context, IconFontTextView iconFontTextView) {
        mContext = context;
        mIconFontTextView = iconFontTextView;
        mDialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mShareMenu = inflater.inflate(R.layout.timedialog,null);

        mHandler = new Handler();

        initdatas();
        ListView mListView =  (ListView)mShareMenu.findViewById(R.id.timelistview);

        mListView.setAdapter(new BaseAdapter<String>(context, mDatas, R.layout.timelistview_item) {
            @Override
            public void convert(ViewHolder helper, final String item, final int position) {
                helper.setText(R.id.timetext,item);
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ((Main)(mContext)).controlTimeView(mShowTimes.get(position));
                        Dismiss();
                    }
                });
            }
        });

        Window window = mDialog.getWindow();
        window.setGravity(Gravity.NO_GRAVITY);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.ButtomDialog);  //添加动画
        mDialog.show();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.addContentView(mShareMenu,params);
        mDialog.dismiss();
    }

//    /**
//     * 控制计时器倒计时
//     * @param startTime
//     */
//    public void controlTimeView(final String startTime){
//
//
//
//        timer = new TickTimer(TimeSpan.fromSeconds(1f), new Runnable() {
//            String mTime = startTime;
//            @Override
//            public void run() {
//
//                if(!mTime.equals("00:00:00") && !mTime.equals(mContext.getResources().getString(R.string.timer))){
//                    mTime =  DecSecond.DecOneSecond(mTime);
//                }else{
//                    mTime = mContext.getResources().getString(R.string.timer);
//                }
//
//                EventBus.getDefault().post(new ShowTimeEvent(mTime));//发布事件
//            }
//        });
//
//    }

    public void Show(){
        mDialog.show();
        if(timer != null && timer.isRunning()){
            timer.stop();
        }
    }

    public void Dismiss(){
        mDialog.dismiss();
        if(timer != null && !timer.isRunning()){
            timer.start();
        }
    }

    private void initdatas() {
        mDatas = new ArrayList<>();
        mShowTimes = new ArrayList<>();

        mDatas.add("No Timer");
        mDatas.add("00:01:00");
        mDatas.add("00:03:00");
        mDatas.add("00:05:00");
        mDatas.add("00:10:00");
        mDatas.add("00:15:00");
        mDatas.add("00:20:00");
        mDatas.add("00:30:00");
        mDatas.add("00:40:00");
        mDatas.add("00:45:00");
        mDatas.add("00:50:00");
        mDatas.add("01:00:00");
        mDatas.add("02:00:00");
        mDatas.add("04:00:00");
        mDatas.add("08:00:00");

        mShowTimes.add("00:00:00");
        mShowTimes.add("00:01:00");
        mShowTimes.add("00:03:00");
        mShowTimes.add("00:05:00");
        mShowTimes.add("00:10:00");
        mShowTimes.add("00:15:00");
        mShowTimes.add("00:20:00");
        mShowTimes.add("00:30:00");
        mShowTimes.add("00:40:00");
        mShowTimes.add("00:45:00");
        mShowTimes.add("00:50:00");
        mShowTimes.add("01:00:00");
        mShowTimes.add("02:00:00");
        mShowTimes.add("04:00:00");
        mShowTimes.add("08:00:00");
        Log.d("DialogControl", TimeStringConVert.getTimeString(mContext, "08:00:00"));
        List<String> temp = new ArrayList<>();
        for (String s:mDatas) {
            temp.add(TimeStringConVert.getTimeString(mContext,s));
            Log.d("DialogControl", TimeStringConVert.getTimeString(mContext, s));
        }

        mDatas = temp;
    }
}
