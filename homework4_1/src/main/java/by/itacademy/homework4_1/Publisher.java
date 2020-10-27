package by.itacademy.homework4_1;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements IObserved {
    private final List<Item> itemList = new ArrayList<>();
    private static Publisher INSTANCE;
    private final List<IObserver> subscribers = new ArrayList<>();

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

    @Override
    public void addSubscriber(IObserver observer) {
        if (!subscribers.contains(observer)) {
            subscribers.add(observer);
        }
    }

    @Override
    public void removeSubscriber(IObserver observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyChanged(int position, int operation) {
        for (IObserver observer : subscribers) {
            observer.notifyDataChange(position, operation);
        }
    }
}
