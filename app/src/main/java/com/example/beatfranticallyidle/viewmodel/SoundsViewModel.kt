package com.example.beatfranticallyidle.viewmodel

import android.app.Application
import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes
import androidx.lifecycle.AndroidViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class SoundsViewModel(application: Application) : AndroidViewModel(application) {

    private var player: ExoPlayer? = null
    private val context: Context = application.applicationContext

    private var soundPool: SoundPool? = null
    private val soundMap: HashMap<Int, Int> = HashMap()

    init {
        initializePlayer()
        initializeSoundPool()
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayer.Builder(context).build()
        }
    }

    private fun initializeSoundPool() {
        soundPool = SoundPool.Builder().setMaxStreams(10).build()
    }

    fun loadSoundEffect(@RawRes rawResourceID: Int, soundId: Int) {
        val loadedSoundId = soundPool?.load(context, rawResourceID, 1) ?: 0
        soundMap[soundId] = loadedSoundId
    }

    fun playBackgroundSound(@RawRes rawResourceID: Int, loop: Boolean = true) {
        val mediaItem = MediaItem
            .fromUri("android.resource://${context.packageName}/$rawResourceID")
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
        player?.repeatMode = if (loop) ExoPlayer.REPEAT_MODE_ALL else ExoPlayer.REPEAT_MODE_OFF
    }

    fun pauseBackgroundSound() {
        player?.playWhenReady = false
    }

    fun resumeBackgroundSound() {
        player?.playWhenReady = true
    }

    fun stopBackgroundSound() {
        player?.stop()
        player?.seekTo(0)
    }

    fun playSoundEffect(soundId: Int) {
        val loadedSoundId = soundMap[soundId] ?: 0
        if (loadedSoundId != 0) {
            soundPool?.play(loadedSoundId, 1f, 1f, 0, 0, 1f)
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    private fun releaseSoundPool() {
        soundPool?.release()
        soundPool = null
        soundMap.clear()
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
        releaseSoundPool()
    }
}