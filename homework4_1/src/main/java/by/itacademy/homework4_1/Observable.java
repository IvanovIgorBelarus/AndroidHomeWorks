package by.itacademy.homework4_1;

public interface Observable {
    void addSubscriber(IObserver observer);

    void removeSubscriber(IObserver observer);

    void notifyChanged(int position, int operation);
}
