package com.one.duanone.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.utils.GlideUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity {

    private static final String TAG = LiveActivity.class.getSimpleName();
    private VideoView videoView;
    private String url;
    private int groupId;
    private TextView name;
    private TextView city;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_live);

        /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        groupId = intent.getIntExtra("groupId", 0);

        initView();

        String iconUrl = intent.getStringExtra("icon");
        String nameStr = intent.getStringExtra("name");
        String cityStr = intent.getStringExtra("city");

        GlideUtils.urlCircleImage(this, iconUrl, icon);
        name.setText(nameStr);
        city.setText(cityStr);


    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.live_view_video_view);
        icon = (ImageView) findViewById(R.id.icon_live);
        name = (TextView) findViewById(R.id.name_live);
        city = (TextView) findViewById(R.id.city_live);
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.setVideoPath(url);
        videoView.requestFocus();
        videoView.setMediaController(new MediaController(this));
        //设置音量大小
        videoView.setVolume(.08f, 0.8f);
        //返回视频的宽高比
        float zoom = videoView.getVideoAspectRatio();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (zoom != 0) {
            int w = getWindowManager().getDefaultDisplay().getWidth();
            int h = getWindowManager().getDefaultDisplay().getHeight();
            if (zoom > 1) {
                lp.height = h;
                lp.width = (int) (h * zoom);
            } else {
                lp.width = w;
                lp.height = (int) (w * zoom);
            }
        }
        videoView.setLayoutParams(lp);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
               mp.setPlaybackSpeed(1.0f);
            }
        });
    }
}
