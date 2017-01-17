package com.greendami.wellrelax;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Dialog.DialogControl;
import Listener.onSeekChangeLisner;
import Module.ACache;
import UI.IconFontTextView;
import UI.Seek;
import business.DecSecond;
import business.TimeStringConVert;
import business.Timer.TickTimer;
import business.Timer.TimeSpan;
import business.VoiceController;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GreendaMi on 2016/11/25.
 */

public class Main extends Activity {
    @Bind(R.id.seek_one)
    Seek mSeekOne;
    @Bind(R.id.seek_two)
    Seek mSeekTwo;
    @Bind(R.id.seek_three)
    Seek mSeekThree;
    @Bind(R.id.seek_four)
    Seek mSeekFour;
    @Bind(R.id.seek_five)
    Seek mSeekFive;
    @Bind(R.id.seek_six)
    Seek mSeekSix;
    @Bind(R.id.seek_seven)
    Seek mSeekSeven;
    @Bind(R.id.seek_eight)
    Seek mSeekEight;
    @Bind(R.id.seek_nine)
    Seek mSeekNine;
    @Bind(R.id.seek_ten)
    Seek mSeekTen;
    @Bind(R.id.seek_eleven)
    Seek mSeekEleven;
    @Bind(R.id.seek_twelve)
    Seek mSeekTwelve;
    @Bind(R.id.seek_thirteen)
    Seek mSeekThirteen;
    @Bind(R.id.seek_fourteen)
    Seek mSeekFourteen;
    @Bind(R.id.seek_favorites)
    View mSeekFavorites;
    @Bind(R.id.play)
    IconFontTextView mPlay;
    @Bind(R.id.timer)
    IconFontTextView mTimer;

    VoiceController mVoiceController;
    DialogControl mDialogControl;
    Handler mHandler;
    TickTimer timer;

    HashMap mMap = new HashMap();
    @Bind(R.id.add)
    IconFontTextView add;
    ArrayList<HashMap> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }


        mVoiceController = new VoiceController();
        mVoiceController.init(this);

        mDialogControl = new DialogControl();
        mDialogControl.initDialog(this, mTimer);

        initEvent();
        mHandler = new Handler();

    }

    /**
     * 注册回调
     */
    private void initEvent() {
        mSeekOne.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(1, per);
            }
        });
        mSeekTwo.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(2, per);
            }
        });
        mSeekThree.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(3, per);
            }
        });
        mSeekFour.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(4, per);
            }
        });
        mSeekFive.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(5, per);
            }
        });
        mSeekSix.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(6, per);
            }
        });
        mSeekSeven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(7, per);
            }
        });
        mSeekEight.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(8, per);
            }
        });
        mSeekNine.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(9, per);
            }
        });
        mSeekTen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(10, per);
            }
        });
        mSeekEleven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(11, per);
            }
        });
        mSeekTwelve.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(12, per);
            }
        });
        mSeekThirteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(13, per);
            }
        });
        mSeekFourteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(14, per);
            }
        });
        mSeekFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, ChooseSaved.class);
                startActivityForResult(intent,1);
            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVoiceController.playOrPause();
                mSeekOne.setCurrent(0);
                mSeekTwo.setCurrent(0);
                mSeekThree.setCurrent(0);
                mSeekFour.setCurrent(0);
                mSeekFive.setCurrent(0);
                mSeekSix.setCurrent(0);
                mSeekSeven.setCurrent(0);
                mSeekEight.setCurrent(0);
                mSeekNine.setCurrent(0);
                mSeekTen.setCurrent(0);
                mSeekEleven.setCurrent(0);
                mSeekTwelve.setCurrent(0);
                mSeekThirteen.setCurrent(0);
                mSeekFourteen.setCurrent(0);
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                mMap.clear();
            }
        });
        mPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mPlay.setScaleX(0.9f);
                    mPlay.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mPlay.setScaleX(1f);
                    mPlay.setScaleY(1f);
                }
                return false;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.size()>0){
                    ACache mCache = ACache.get(Main.this);
                    if(mCache.getAsObject("List") != null){
                        mList = (ArrayList<HashMap>) mCache.getAsObject("List");
                    }
                    mList.add(mMap);
                    mCache.put("List",mList);
                    Toast.makeText(Main.this, "保存成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    add.setScaleX(0.9f);
                    add.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    add.setScaleX(1f);
                    add.setScaleY(1f);
                }
                return false;
            }
        });

        mTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogControl.Show();
            }
        });

        mTimer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mTimer.setScaleX(0.9f);
                    mTimer.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mTimer.setScaleX(1f);
                    mTimer.setScaleY(1f);
                }
                return false;
            }
        });

    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVoiceController.playOrPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }


//    @Subscribe
//    public void onEventMainThread(final ShowTimeEvent showTimeEvent) {
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                mTimer.setText(TimeStringConVert.getTimeString(Main.this,showTimeEvent.getS()));
//
//                //带冒号，说明是时间，设置大字体
//                if(!showTimeEvent.getS().contains(":")){
//                    mTimer.setTextSize(35);
//                }else{
//                    mTimer.setTextSize(20);
//                }
//
//                if(showTimeEvent.getS().equals("00:00:00")){
//                    mPlay.callOnClick();
//                }
//            }
//        });
//
//    }

    private void SHowNotification() {
        NotificationManager manger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        builder.setTicker("WellRelax");
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("正在运行");
        //第二行内容 通常是通知正文
        builder.setContentText("Be Relax!");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(1);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(false);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.logo2);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo2));
        Intent intent = new Intent(this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(Main.this, 1, intent, 0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE);
        Notification notification = builder.build();
        manger.notify(177, notification);
    }

    public void control(int index, int per) {
        mVoiceController.voiceControl(index, per);
        SHowNotification();
        mMap.remove(index);
        if (per != 0) {
            mMap.put(index, per);
        }
        if (mMap.isEmpty()) {
            NotificationManager manger = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            manger.cancel(177);
        }

    }

    /**
     * 控制计时器倒计时
     *
     * @param startTime
     */
    public void controlTimeView(final String startTime) {


        if(timer != null && timer.isRunning()){
            timer.stop();
        }
        timer = new TickTimer(TimeSpan.fromSeconds(1f), new Runnable() {
            String mTime = startTime;

            @Override
            public void run() {

                if (!mTime.equals("00:00:00") && !mTime.equals(getResources().getString(R.string.timer))) {
                    mTime = DecSecond.DecOneSecond(mTime);
                } else {
                    mTime = getResources().getString(R.string.timer);
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTimer.setText(TimeStringConVert.getTimeString(Main.this, mTime));

                        //带冒号，说明是时间，设置大字体
                        if (!mTime.contains(":")) {
                            mTimer.setTextSize(35);
                        } else {
                            mTimer.setTextSize(14);
                        }

                        if (mTime.equals("00:00:00")) {
                            mPlay.callOnClick();
                        }

                        if (!timer.isRunning()) {
                            mTimer.setText(getResources().getString(R.string.timer));
                            mTimer.setTextSize(35);
                        }
                    }
                });
            }
        });
        timer.start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int result = -1;
        if(data != null){
            result = data.getExtras().getInt("result", -1);//得到新Activity 关闭后返回的数据
        }

        ACache mCache = ACache.get(Main.this);
        if(mCache.getAsObject("List") != null){
            mList = (ArrayList<HashMap>) mCache.getAsObject("List");
        }
        if(mList.size() > result && result != -1){
            mMap = mList.get(result);
            //重置播放状态
            mVoiceController.playOrPause();
            mSeekOne.setCurrent(0);
            mSeekTwo.setCurrent(0);
            mSeekThree.setCurrent(0);
            mSeekFour.setCurrent(0);
            mSeekFive.setCurrent(0);
            mSeekSix.setCurrent(0);
            mSeekSeven.setCurrent(0);
            mSeekEight.setCurrent(0);
            mSeekNine.setCurrent(0);
            mSeekTen.setCurrent(0);
            mSeekEleven.setCurrent(0);
            mSeekTwelve.setCurrent(0);
            mSeekThirteen.setCurrent(0);
            mSeekFourteen.setCurrent(0);

            SHowNotification();
            Iterator<Map.Entry<Integer, Integer>> it = mMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                int in = entry.getKey();
                int value = entry.getValue();
                mVoiceController.voiceControl(in,value);

                switch (in){
                    case 1:
                        mSeekOne.setCurrent(value);
                        break;
                    case 2:
                        mSeekTwo.setCurrent(value);
                        break;
                    case 3:
                        mSeekThree.setCurrent(value);
                        break;
                    case 4:
                        mSeekFour.setCurrent(value);
                        break;
                    case 5:
                        mSeekFive.setCurrent(value);
                        break;
                    case 6:
                        mSeekSix.setCurrent(value);
                        break;
                    case 7:
                        mSeekSeven.setCurrent(value);
                        break;
                    case 8:
                        mSeekEight.setCurrent(value);
                        break;
                    case 9:
                        mSeekNine.setCurrent(value);
                        break;
                    case 10:
                        mSeekTen.setCurrent(value);
                        break;
                    case 11:
                        mSeekEleven.setCurrent(value);
                        break;
                    case 12:
                        mSeekTwelve.setCurrent(value);
                        break;
                    case 13:
                        mSeekThirteen.setCurrent(value);
                        break;
                    case 14:
                        mSeekFourteen.setCurrent(value);
                        break;
                }
            }

        }
    }
}
