package business;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.greendami.wellrelax.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GreendaMi on 2016/11/25.
 */

public class VoiceController {

    Context mContext;
    boolean isPlay = false;

    List<MediaPlayer> mMediaPlayer  = new ArrayList<>();

    /**
     * 初始化声音播放器
     * @param context
     */
    public void init(Context context)  {
        mContext = context;

        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.rain));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.thunders));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.rain_on_window));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.car));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.wind));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.forest));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.creek));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.leaves));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.fire));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.ocean));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.train));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.night));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.cafe));
        mMediaPlayer.add(MediaPlayer.create(mContext, R.raw.whitenoise));

        for (final MediaPlayer m:mMediaPlayer) {
            m.setAudioStreamType(AudioManager.STREAM_MUSIC);
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    m.start();
                }
            });
        }

    }

    /**
     * 音量控制
     * @param id
     * @param per
     */
    public void voiceControl(int id , int per){
        if(!mMediaPlayer.get(id - 1).isPlaying()){
            mMediaPlayer.get(id - 1).start();

        }
        mMediaPlayer.get(id - 1).setVolume(per/100.0f,per/100.0f);
        if( per == 0 ){
            if(mMediaPlayer.get(id - 1).isPlaying()){
                mMediaPlayer.get(id - 1).pause();
            }
        }

    }

    /**
     * 暂停所有播放
     */
    public void playOrPause() {
        for (final MediaPlayer m:mMediaPlayer) {
            m.setAudioStreamType(AudioManager.STREAM_MUSIC);
            m.setLooping(true);
            if(m.isPlaying()){
                m.pause();
            }
        }
    }


    public boolean isPlay(){
        return isPlay;
    }
}
