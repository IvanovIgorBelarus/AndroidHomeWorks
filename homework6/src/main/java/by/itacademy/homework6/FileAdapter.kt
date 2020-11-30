package by.itacademy.homework6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileAdapter(private val fileList: List<String>,
                  private val fileActionListener: FileActionListener) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return FileViewHolder(view)
    }

    override fun getItemCount(): Int = fileList.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(fileList[position])
        holder.itemView.setOnClickListener { fileActionListener.onFileClicked(position) }
    }

    class FileViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private var name = itemView.findViewById<TextView>(R.id.fileName)
        fun bind(fileItem: String) {
            name.text = fileItem
        }
    }
}