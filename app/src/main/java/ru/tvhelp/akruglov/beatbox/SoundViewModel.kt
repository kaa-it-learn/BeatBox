package ru.tvhelp.akruglov.beatbox

import android.databinding.BaseObservable
import android.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {

    private var _sound: Sound? = null

    var sound: Sound?
        get() = _sound
        set(value) {
            _sound = value
            notifyChange()
        }

    @Bindable
    fun getTitle() = sound?.name

    fun onButtonClicked() {
        beatBox.play(sound!!)
    }
}