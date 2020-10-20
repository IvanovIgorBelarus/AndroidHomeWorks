package by.itacademy.homework2observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements IObserved {
    private static Publisher INSTANCE;
    private static ArrayList<Integer> data;
    private static String result;

    public static Publisher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Publisher();
        }
        return INSTANCE;
    }

    public static ArrayList<Integer> getData() {
        return data;
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        Publisher.result = result;
    }

    public static void setData(ArrayList<Integer> data) {
        Publisher.data = data;
    }

    private Publisher() {
    }

    private final List<IObserver> subscribers = new ArrayList<>();


    @Override
    public void addSubscriber(IObserver observer) {
        if (!subscribers.contains(observer)) {
            subscribers.add(observer);
        }
        notifySubscribers();
    }

    @Override
    public void removeSubscriber(IObserver observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (IObserver observer : subscribers) {
            observer.notifyDataChanged();
        }
    }
}
