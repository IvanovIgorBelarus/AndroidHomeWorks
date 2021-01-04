package by.itacademy.homework10.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.homework10.R
import by.itacademy.homework10.TAG
import by.itacademy.homework10.databinding.RecyclerViewBinding
import by.itacademy.homework10.model.MusicModel

class MusicTitleAdapter(
        private val musicListener: MusicListener
) : RecyclerView.Adapter<MusicTitleAdapter.MusicViewHolder>() {
    private val titleList: MutableList<MusicModel> = mutableListOf()
    private var selectedItemId = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MusicViewHolder(RecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        with(holder) {
            bind(titleList[position], position)
            itemView.setOnClickListener { musicListener.playThisSong(position) }
        }
    }

    fun setLiveData(list: List<MusicModel>) {
        with(titleList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun upDateAdapter(position: Int) {
        selectedItemId = position
        Log.d(TAG, "upDateAdapter $selectedItemId")
        notifyDataSetChanged()
    }

    override fun getItemCount() = titleList.size

    inner class MusicViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(musicModel: MusicModel, id: Int) {
            binding.titleTextView.text = musicModel.musicTitle
            if (id == selectedItemId) {
                Log.d(TAG, "setImage $selectedItemId")
                binding.imageView.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
            }
        }
    }
}