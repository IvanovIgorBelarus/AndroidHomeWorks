package by.itacademy.homework10.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.homework10.databinding.RecyclerViewBinding
import by.itacademy.homework10.model.MusicModel

class MusicTitleAdapter(
        private val titleList: MutableList<MusicModel> = mutableListOf()
) : RecyclerView.Adapter<MusicTitleAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MusicViewHolder(RecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(titleList[position])
    }

    fun updateAdapter(list: List<MusicModel>) {
        with(titleList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = titleList.size

    class MusicViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(musicModel: MusicModel) {
            binding.titleTextView.text = musicModel.musicTitle
        }
    }
}