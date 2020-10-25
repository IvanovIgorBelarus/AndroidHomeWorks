package by.itacademy.homework4_1;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Item> itemList = new ArrayList<>();
    private static Publisher INSTANCE;

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(int position) {
        itemList.remove(itemList.get(position));
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public static Publisher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Publisher();
        }
        return INSTANCE;
    }

    private Publisher() {
    }


}
