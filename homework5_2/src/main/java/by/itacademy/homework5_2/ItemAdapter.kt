package by.itacademy.homework5_2

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val listItemActionListener: ListItemActionListener) :
        RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val contactList= mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(contactList[position])
        holder.itemView.setOnClickListener { listItemActionListener.onItemClicked(position) }
    }
    fun updateItem(list:List<Contact>){
        contactList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name = itemView.findViewById<TextView>(R.id.textName)
        private var info = itemView.findViewById<TextView>(R.id.textInfo)
        private var image = itemView.findViewById<ImageView>(R.id.imageRes)

        fun bind(contact: Contact) {
            name.text = contact.name
            info.text = contact.data
            if (contact.isPhone==1) {
                image.apply {
                    setImageResource(R.drawable.ic_contact_phone_white_48dp)
                    setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                }
            } else image.apply {
                setImageResource(R.drawable.ic_contact_phone_white_48dp)
                setColorFilter(resources.getColor(R.color.colorPink))
            }
        }
    }
}