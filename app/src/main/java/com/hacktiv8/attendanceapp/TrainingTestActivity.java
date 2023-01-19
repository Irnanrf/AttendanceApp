package com.hacktiv8.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.appbar.AppBarLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;

public class TrainingTestActivity extends AppCompatActivity {

    ImageButton btnBackTrainingTest;
    Button btnNextTraining;
    TextView lblVideoPlayerTitle;
    AppBarLayout toolbarParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_training_test);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        lblVideoPlayerTitle = findViewById(R.id.lblVideoPlayerTitle);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String videoTitle = bundle.getString("videoTitle");
            lblVideoPlayerTitle.setText(videoTitle);
        }

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "";
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    videoId = bundle.getString("videoSource");
                }
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        btnNextTraining = findViewById(R.id.btnNextTraining);
        btnNextTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainingTestActivity.this, TrainingHomeActivity.class);
                i.putExtra("videoTitle", lblVideoPlayerTitle.getText().toString());
                startActivity(i);

            }
        });

        btnBackTrainingTest = findViewById(R.id.btnBackTrainingTest);
        btnBackTrainingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainingTestActivity.this, TrainingHomeActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        toolbarParent = findViewById(R.id.toolbarParent);
        Log.d("tag", "config changed");
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            toolbarParent.setVisibility(View.VISIBLE);
            Log.d("tag", "Portrait");
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            toolbarParent.setVisibility(View.GONE);
            Log.d("tag", "Landscape");
        }
    }
}