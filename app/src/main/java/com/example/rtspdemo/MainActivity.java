package com.example.rtspdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.example.rtspdemo.widget.media.AndroidMediaController;
import com.example.rtspdemo.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private IjkVideoView mVideoView = null;

    private TableLayout mHudView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        setContentView(R.layout.activity_main);
        initView();

        AndroidMediaController controller = new AndroidMediaController(this, false);
        mVideoView.setMediaController(controller);
        mVideoView.setHudView(mHudView);
//        String url = "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";
        String url = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
        // String url = "http://o6wf52jln.bkt.clouddn.com/演员.mp3";
        mVideoView.setVideoURI(Uri.parse(url));
    }

    private void initView() {
        mVideoView = findViewById(R.id.video_view);
        mHudView = findViewById(R.id.hud_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
        mVideoView.start();
    }
}
