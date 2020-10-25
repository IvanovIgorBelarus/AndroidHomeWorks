package by.itacademy.homework4_1;

public interface IObserved {
    void addSubscriber(ItemAdapter observer);
    void removeSubscriber(ItemAdapter observer);
    void notifySubscribers();
}
