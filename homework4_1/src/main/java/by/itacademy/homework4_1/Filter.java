package by.itacademy.homework4_1;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static List<Item> filter(String text) {
        List<Item> items = Publisher.getInstance().getItemList();
        List<Item> itemsCopy = new ArrayList<>();
        text = text.toLowerCase();
        if (text.isEmpty()) {
            return items;
        }
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(text)) {
                itemsCopy.add(item);
            }
        }
        if (itemsCopy.size() == 0) {
            return items;
        }
        return itemsCopy;
    }
}
