package by.itacademy.homework4_1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private MainActivity.ListItemActionListener listItemActionListener;
    private List<Item> items;

    public void setItems(List<Item> items) {
        Log.d("HM4", String.format(" setItems, size=%s ", items.size()));
        this.items = items;
    }

    public ItemAdapter(List<Item> items, MainActivity.ListItemActionListener listItemActionListener) {
        this.items = items;
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
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView info;
        private ImageView image;
        private MainActivity.ListItemActionListener listItemActionListener;

        public ItemViewHolder(@NonNull View itemView, MainActivity.ListItemActionListener listItemActionListener) {
            super(itemView);
            image = itemView.findViewById(R.id.imageRes);
            name = itemView.findViewById(R.id.textName);
            info = itemView.findViewById(R.id.textInfo);
            this.listItemActionListener = listItemActionListener;
        }

        public void bind(Item item) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listItemActionListener != null) {
                        listItemActionListener.onItemClicked(getAdapterPosition());
                    }
                }
            });
            name.setText(item.getName());
            if (item.getPhone() != null) {
                info.setText(item.getPhone());
                image.setImageResource(R.drawable.ic_contact_phone_white_48dp);
                image.setColorFilter(image.getResources().getColor(R.color.colorPrimaryDark));
            }
            if (item.getEmail() != null) {
                info.setText(item.getEmail());
                image.setImageResource(R.drawable.ic_contact_mail_white_48dp);
                image.setColorFilter(image.getResources().getColor(R.color.colorPink));
            }
        }
    }
}
