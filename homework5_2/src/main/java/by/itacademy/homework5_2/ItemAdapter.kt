package by.itacademy.homework5_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val contactList: List<Contact>,
                  private val listItemActionListener: ListItemActionListener) :
        RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(contactList[position])
        holder.itemView.setOnClickListener { listItemActionListener.onItemClicked(position) }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name = itemView.findViewById<TextView>(R.id.textName)
        private var info = itemView.findViewById<TextView>(R.id.textInfo)
        private var image = itemView.findViewById<ImageView>(R.id.imageRes)

        fun bind(contact: Contact) {
            name.text = contact.name
            info.text = contact.data
            if (contact.isPhone) {
                image.run {
                    setImageResource(R.drawable.ic_contact_phone_white_48dp)
                    setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                }
            } else image.run {
                setImageResource(R.drawable.ic_contact_phone_white_48dp)
                setColorFilter(resources.getColor(R.color.colorPink))
            }
        }
    }
}