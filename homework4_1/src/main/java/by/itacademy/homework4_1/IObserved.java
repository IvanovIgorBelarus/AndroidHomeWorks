package by.itacademy.homework4_1;

public interface IObserved {
    void addSubscriber(IObserver observer);
    void removeSubscriber(IObserver observer);
    void notifyChanged(int position, int operation);
}
