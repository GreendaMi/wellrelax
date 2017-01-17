package business;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.greendami.wellrelax.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GreendaMi on 2016/11/25.
 */

public class VoiceController {

    Context mContext;
    boolean isPlay = false;

    Map<String, MediaPlayer> mMediaPlayer = new HashMap<>();

    /**
     * 初始化声音播放器
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;


    }

    /**
     * 音量控制
     *
     * @param id
     * @param per
     */
    public void voiceControl(final int id, int per) {
        if (!mMediaPlayer.containsKey(id + "")) {
            switch (id) {
                case 1:
                    mMediaPlayer.put("1", MediaPlayer.create(mContext, R.raw.rain));
                    break;
                case 2:
                    mMediaPlayer.put("2", MediaPlayer.create(mContext, R.raw.thunders));
                    break;
                case 3:
                    mMediaPlayer.put("3", MediaPlayer.create(mContext, R.raw.rain_on_window));
                    break;
                case 4:
                    mMediaPlayer.put("4", MediaPlayer.create(mContext, R.raw.car));
                    break;
                case 5:
                    mMediaPlayer.put("5", MediaPlayer.create(mContext, R.raw.wind));
                    break;
                case 6:
                    mMediaPlayer.put("6", MediaPlayer.create(mContext, R.raw.forest));
                    break;
                case 7:
                    mMediaPlayer.put("7", MediaPlayer.create(mContext, R.raw.creek));
                    break;
                case 8:
                    mMediaPlayer.put("8", MediaPlayer.create(mContext, R.raw.leaves));
                    break;
                case 9:
                    mMediaPlayer.put("9", MediaPlayer.create(mContext, R.raw.fire));
                    break;
                case 10:
                    mMediaPlayer.put("10", MediaPlayer.create(mContext, R.raw.ocean));
                    break;
                case 11:
                    mMediaPlayer.put("11", MediaPlayer.create(mContext, R.raw.train));
                    break;
                case 12:
                    mMediaPlayer.put("12", MediaPlayer.create(mContext, R.raw.night));
                    break;
                case 13:
                    mMediaPlayer.put("13", MediaPlayer.create(mContext, R.raw.cafe));
                    break;
                case 14:
                    mMediaPlayer.put("14", MediaPlayer.create(mContext, R.raw.whitenoise));
                    break;
            }
            mMediaPlayer.get(id + "").setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.get(id + "").setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mMediaPlayer.get(id + "").start();
                }
            });
        }
        if (!mMediaPlayer.get(id + "").isPlaying()) {
            mMediaPlayer.get(id + "").start();

        }
        mMediaPlayer.get(id + "").setVolume(per / 100.0f, per / 100.0f);
        if (per == 0) {
            if (mMediaPlayer.get(id + "").isPlaying()) {
                mMediaPlayer.get(id + "").pause();
                mMediaPlayer.get(id + "").release();
                mMediaPlayer.remove(id + "");
            }
        }

    }

    /**
     * 暂停所有播放
     */
    public void playOrPause() {
        for (final String  key : mMediaPlayer.keySet()) {
            mMediaPlayer.get(key).setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.get(key).setLooping(true);
            if (mMediaPlayer.get(key).isPlaying()) {
                mMediaPlayer.get(key).pause();
            }
        }
    }


    public boolean isPlay() {
        return isPlay;
    }
}
