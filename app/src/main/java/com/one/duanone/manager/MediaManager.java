package com.one.duanone.manager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/3  16:05.
 */
public class MediaManager implements SurfaceHolder.Callback {

    private static MediaManager instance;
    private static SurfaceHolder surfaceHolder;
    private PlayListener listener;
    private String url;
    private static SurfaceView surfaceView;
    private static MediaPlayer mediaPlayer;

    //用来通知,ui更新播放进度
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (listener != null && mediaPlayer != null) {
                listener.playing(mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration());
                handler.sendEmptyMessageDelayed(0, 500);
                if (surfaceView.getVisibility() != View.VISIBLE) {
                    clear();
                }
            }
        }
    };
    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (listener != null) {
                listener.prepared();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (mp.isPlaying()){
                listener.notPlay();
                mp.release();
                handler.removeMessages(0);
            }

        }
    };

    private MediaManager() {

    }

    public static MediaManager getInstance(SurfaceView s) {
        if (instance == null) {
            instance = new MediaManager();
        }
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        surfaceView = s;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        return instance;
    }

    /**
     * 播放
     *
     * @param url
     * @param listener
     */
    public void paly(String url, PlayListener listener) {
        this.url = url;
        this.listener = listener;
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(this);
        surfaceHolder.addCallback(this);

        handler.sendEmptyMessage(0);
    }

    /**
     * 暂停
     */
    public void pasue() {
        sendNoPlay();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * 通知停止更新
     */
    public void sendNoPlay() {
        if (listener != null) {
            listener.notPlay();
            handler.removeMessages(0);
        }
    }

    /**
     * 清除
     */
    public void clear() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        sendNoPlay();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            Log.i("TAG", "surfaceCreated: " + url);
            mediaPlayer.reset();//重置为初始状态
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音乐流的类型
            mediaPlayer.setDisplay(holder);//设置video影片以surfaceviewholder播放
            mediaPlayer.setDataSource(url);//设置路径
            mediaPlayer.setOnPreparedListener(preparedListener);
            mediaPlayer.setOnCompletionListener(completionListener);
            mediaPlayer.prepare();//缓冲
            mediaPlayer.start();//播放

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pasue();
    }

    public interface PlayListener {
        /**
         * Created DKL 没有在播放, 播放停止等,,,
         *
         * @return
         * @parent
         */
        void notPlay();

        /**
         * Created DKL
         *
         * @return
         * @parent
         */
        void prepared();

        /**
         * Created DKL 正在播放的时候,调用, 每0.5秒调用一次
         *
         * @return
         * @parent
         */
        void playing(int current, int duration);
    }
}
