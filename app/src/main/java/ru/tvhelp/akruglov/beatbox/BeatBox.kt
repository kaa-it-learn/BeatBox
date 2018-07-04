package ru.tvhelp.akruglov.beatbox

import android.content.res.AssetManager
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

class BeatBox(context: Context, private val assets: AssetManager = context.assets) {

    companion object {
        const val TAG = "BeatBox"
        const val SOUNDS_FOLDER = "sample_sounds"
        const val MAX_SOUNDS = 5
    }

    val sounds = arrayListOf<Sound>()
    val soundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)

    init {
        loadSounds()
    }

    fun play(sound: Sound) {
        val soundId = sound.soundId
        soundId?.let {
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun release() {
        soundPool.release()
    }

    private fun loadSounds() {
        try {
            val soundNames = assets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found " + soundNames.size + " sounds")

            for (fileName in soundNames) {
                    val assetPath = SOUNDS_FOLDER + "/" + fileName
                    val sound = Sound(assetPath)
                    load(sound)
                    sounds.add(sound)
            }
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }
    }

    private fun load(sound: Sound) {
        val afd = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}