package by.itacademy.homework4_1;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements IObserved {
    private static List<Item> itemList = new ArrayList<>();
    private static List<ItemAdapter> observers=new ArrayList<>();
    private static Publisher INSTANCE;

    public static void changeList(Item item) {
        itemList.add(item);

    }

    public static List<Item> getItemList() {
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

    @Override
    public void addSubscriber(ItemAdapter observer) {
        if (!observers.contains(observer)){
            observers.add(observer);
        }
        observer.notifyChange(itemList);
    }

    @Override
    public void removeSubscriber(ItemAdapter observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (ItemAdapter observer : observers) {
            observer.notifyChange(itemList);
        }

    }
}
