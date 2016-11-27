package com.greendami.wellrelax;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import Dialog.DialogControl;
import Event.ShowTimeEvent;
import Listener.onSeekChangeLisner;
import UI.IconFontTextView;
import UI.Seek;
import business.TimeStringConVert;
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
    Seek mSeekFavorites;
    @Bind(R.id.play)
    IconFontTextView mPlay;
    @Bind(R.id.timer)
    IconFontTextView mTimer;

    VoiceController mVoiceController;
    DialogControl mDialogControl;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        EventBus.getDefault().register(this);

        mVoiceController = new VoiceController();
        mVoiceController.init(this);

        mDialogControl = new DialogControl();
        mDialogControl.initDialog(this,mTimer);

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
                mVoiceController.voiceControl(1 , per);
                Log.d("Main", "per:" + per);
            }
        });
        mSeekTwo.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(2 , per);
            }
        });
        mSeekThree.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(3 , per);
            }
        });
        mSeekFour.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(4 , per);
            }
        });
        mSeekFive.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(5 , per);
            }
        });
        mSeekSix.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(6 , per);
            }
        });
        mSeekSeven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(7 , per);
            }
        });
        mSeekEight.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(8 , per);
            }
        });
        mSeekNine.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(9 , per);
            }
        });
        mSeekTen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(10 , per);
            }
        });
        mSeekEleven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(11 , per);
            }
        });
        mSeekTwelve.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(12 , per);
            }
        });
        mSeekThirteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(13 , per);
            }
        });
        mSeekFourteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                mVoiceController.voiceControl(14 , per);
            }
        });
        mSeekFavorites.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                //mVoiceController.voiceControl(15 , per);
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
            }
        });
        mPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    mPlay.setScaleX(0.9f);
                    mPlay.setScaleY(0.9f);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mPlay.setScaleX(1f);
                    mPlay.setScaleY(1f);
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
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    mTimer.setScaleX(0.9f);
                    mTimer.setScaleY(0.9f);
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
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
        EventBus.getDefault().unregister(this);
    }



    @Subscribe
    public void onEventMainThread(final ShowTimeEvent showTimeEvent) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTimer.setText(TimeStringConVert.getTimeString(Main.this,showTimeEvent.getS()));

                if(!showTimeEvent.getS().contains(":")){
                    mTimer.setTextSize(35);
                }else{
                    mTimer.setTextSize(20);
                }

                if(showTimeEvent.getS().equals("00:00:00")){
                    mPlay.callOnClick();
                }
            }
        });

    }
}
