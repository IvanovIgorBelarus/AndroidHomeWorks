package by.itacademy.homework4_1;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements Observable {
    private final List<Contact> contactList = new ArrayList<>();
    private static Publisher INSTANCE;
    private final List<IObserver> subscribers = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
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

    public void addItem(Contact contact) {
        contactList.add(contact);
    }

    public void setItem(int position, Contact contact) {
        contactList.set(position, contact);
        notifyChanged(position, OperationType.CHANGE);
    }

    public void removeItem(int position) {
        contactList.remove(position);
        notifyChanged(position, OperationType.REMOVE);
    }
}
