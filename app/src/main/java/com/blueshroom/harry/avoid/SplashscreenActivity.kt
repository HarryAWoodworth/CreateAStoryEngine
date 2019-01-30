package com.blueshroom.harry.avoid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.media.AudioManager
import android.media.MediaPlayer

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mMediaPlayer = MediaPlayer.create(this, R.raw.leaves)
        mMediaPlayer.start()

        // Crude but works
        while(mMediaPlayer.isPlaying){}

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}