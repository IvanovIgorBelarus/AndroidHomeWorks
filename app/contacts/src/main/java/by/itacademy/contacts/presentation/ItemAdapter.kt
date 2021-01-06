package by.itacademy.contacts.presentation

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.contacts.data.Contact
import by.itacademy.contacts.R
import by.itacademy.contacts.databinding.ItemRecyclerviewBinding


class ItemAdapter(private val listItemActionListener: ListItemActionListener) :
        RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val contactList = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = from(parent.context)
        val binding = ItemRecyclerviewBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(contactList[position])
        holder.itemView.setOnClickListener { listItemActionListener.onItemClicked(position) }
    }

    fun updateItem(list: List<Contact>) {
        contactList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(private var binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.textName.text = contact.name
            binding.textInfo.text = contact.data
            if (contact.isPhone == 1) {
                binding.imageRes.apply {
                    setImageResource(R.drawable.ic_baseline_contact_phone_24)
                    setColorFilter(resources.getColor(R.color.colorPrimaryDark))
                }
            } else binding.imageRes.apply {
                setImageResource(R.drawable.ic_baseline_contact_phone_24)
                setColorFilter(resources.getColor(R.color.colorPink))
            }
        }
    }
}