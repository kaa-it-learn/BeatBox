package ru.tvhelp.akruglov.beatbox

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.tvhelp.akruglov.beatbox.databinding.FragmentBeatBoxBinding
import ru.tvhelp.akruglov.beatbox.databinding.ListItemSoundBinding

class BeatBoxFragment() : Fragment() {

    companion object {
        fun newInstance() = BeatBoxFragment()
    }

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        beatBox = BeatBox(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentBeatBoxBinding>(inflater, R.layout.fragment_beat_box, container, false)

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = SoundAdapter(beatBox.sounds)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound) {
            binding.viewModel!!.sound = sound
            binding.executePendingBindings()
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>): RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val inflater = LayoutInflater.from(activity)
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(inflater, R.layout.list_item_sound, parent, false)
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(soundHolder: SoundHolder, position: Int) {
            val sound = sounds[position]
            soundHolder.bind(sound)
        }

        override fun getItemCount(): Int {
            return sounds.size
        }
    }
}