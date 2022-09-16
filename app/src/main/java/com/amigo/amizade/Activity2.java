package com.amigo.amizade;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private MediaPlayer mp;
    private ToggleButton musicButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mp = MediaPlayer.create(this, R.raw.alice);
        this.musicButton = findViewById(R.id.music_button);
        this.musicButton.setOnCheckedChangeListener((v, c) -> musicToggle(c));
    }

    public void onBackPressed() {
        mp.stop();
        finish();
    }

    private void musicToggle(Boolean checked) {
        if (checked) {
            this.mp.start();
        } else {
            this.mp.pause();
        }
    }
}