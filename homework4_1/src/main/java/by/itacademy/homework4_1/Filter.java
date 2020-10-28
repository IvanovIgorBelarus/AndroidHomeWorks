package by.itacademy.homework4_1;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static List<Item> filter(String text) {
        List<Item> items = Publisher.getInstance().getItemList();
        List<Item> itemsCopy = new ArrayList<>();
        text = text.toLowerCase();
        if (text.isEmpty()) {
            Log.d("HM4", "return ITEMS, text is empty ");
            return items;
        }
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(text)) {
                itemsCopy.add(item);
                Log.d("HM4", String.format("add itemName=%s, size=%s ", item.getName().toLowerCase(), itemsCopy.size()));
            }
        }
        if (itemsCopy.size() == 0) {
            Log.d("HM4", "return ITEMS itemCopy= " + itemsCopy.size());
            return items;
        }
        Log.d("HM4", "return COPY itemCopy= " + itemsCopy.size());
        return itemsCopy;
    }
}
