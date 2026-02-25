package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.WindowManager
import android.widget.VideoView
import androidx.core.net.toUri

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var videoView: VideoView

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        videoView = VideoView(this)

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // וידאו מה־raw
        val videoPath = "android.resource://$packageName/${R.raw.michal}"
        val uri = videoPath.toUri()

        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener {
            videoView.start()
        }

        videoView.setOnCompletionListener {
            stopSelf() // סוגר את השירות
        }

        windowManager.addView(videoView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeView(videoView)
    }
}