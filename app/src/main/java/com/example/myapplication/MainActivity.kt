package com.example.myapplication

import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoView = VideoView(this)
        setContentView(videoView)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.michal
        val uri = videoPath.toUri()
        videoView.setVideoURI(uri)

        // התחלה אוטומטית
        videoView.setOnPreparedListener {
            videoView.start()
        }

        // כשהוידאו נגמר → יציאה מהאפליקציה
        videoView.setOnCompletionListener {
            finishAffinity() // סוגר את האפליקציה
        }
    }

    override fun onResume() {
        super.onResume()
        videoView.start() // תמיד מתחיל מהתחלה כשנכנסים
    }
}