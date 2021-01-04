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
    private var selectedItemTitle = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MusicViewHolder(RecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        with(holder) {
            bind(titleList[position])
            itemView.setOnClickListener { musicListener.playThisSong(titleList[position].musicTitle) }
        }
    }

    fun setLiveData(list: List<MusicModel>) {
        with(titleList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun upDateAdapter(title: String) {
        selectedItemTitle = title
        Log.d(TAG, "upDateAdapter $selectedItemTitle")
        notifyDataSetChanged()
    }

    override fun getItemCount() = titleList.size

    inner class MusicViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(musicModel: MusicModel) {
            binding.titleTextView.text = musicModel.musicTitle
            if (musicModel.musicTitle == selectedItemTitle) {
                binding.imageView.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
            }
        }
    }
}