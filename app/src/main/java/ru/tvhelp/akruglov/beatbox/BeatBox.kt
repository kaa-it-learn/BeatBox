package ru.tvhelp.akruglov.beatbox

import android.content.res.AssetManager
import android.content.Context
import android.util.Log
import java.io.IOException

class BeatBox(context: Context, private val assets: AssetManager = context.assets) {

    companion object {
        const val TAG = "BeatBox"
        const val SOUNDS_FOLDER = "sample_sounds"
    }

    val sounds = arrayListOf<Sound>()

    init {
        loadSounds()
    }

    private fun loadSounds() {
        try {
            val soundNames = assets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found " + soundNames.size + " sounds")

            for (fileName in soundNames) {
                val assetPath = SOUNDS_FOLDER + "/" + fileName
                val sound = Sound(assetPath)
                sounds.add(sound)
            }
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }


    }
}