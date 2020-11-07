package by.itacademy.homework4_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    interface ListItemActionListener {
        void onItemClicked(int number);
    }

    private ListItemActionListener listItemActionListener;
    private List<Contact> contacts;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ItemAdapter(List<Contact> contacts, ListItemActionListener listItemActionListener) {
        this.contacts = contacts;
        this.listItemActionListener = listItemActionListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new ItemViewHolder(view, listItemActionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        }
        return 0;
    }

    public void filter(String text) {
        List<Contact> itemsCopy = new ArrayList<>();
        text = text.toLowerCase();
        if (text.isEmpty()) {
            setContacts(contacts);
            notifyDataSetChanged();
            return;
        }
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(text)) {
                itemsCopy.add(contact);
            }
        }
        setContacts(itemsCopy);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView info;
        private ImageView image;
        private ListItemActionListener listItemActionListener;

        public ItemViewHolder(@NonNull View itemView, ListItemActionListener listItemActionListener) {
            super(itemView);
            image = itemView.findViewById(R.id.imageRes);
            name = itemView.findViewById(R.id.textName);
            info = itemView.findViewById(R.id.textInfo);
            this.listItemActionListener = listItemActionListener;
        }

        public void bind(Contact contact) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listItemActionListener != null) {
                        listItemActionListener.onItemClicked(getAdapterPosition());
                    }
                }
            });
            name.setText(contact.getName());
            info.setText(contact.getData());
            if (contact.isPhone()) {
                image.setImageResource(R.drawable.ic_contact_phone_white_48dp);
                image.setColorFilter(image.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                image.setImageResource(R.drawable.ic_contact_mail_white_48dp);
                image.setColorFilter(image.getResources().getColor(R.color.colorPink));
            }
        }
    }
}
