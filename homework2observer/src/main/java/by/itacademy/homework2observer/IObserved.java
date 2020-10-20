package by.itacademy.homework2observer;

public interface IObserved {
    void addSubscriber(IObserver observer);

    void removeSubscriber(IObserver observer);

    void notifySubscribers();
}
