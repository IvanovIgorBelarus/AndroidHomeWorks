package by.itacademy.homework5_2

interface Observable {
    fun addSubscriber(subscriber: Observer)
    fun removeSubscriber(subscriber: Observer)
    fun notifySubscribers(position: Int, operation: Int)
}