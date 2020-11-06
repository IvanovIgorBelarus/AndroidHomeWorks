package by.itacademy.homework5_2

class Data private constructor() : Observable {
    private object DataHolder {
        val INSTANCE = Data()
    }

    companion object {
        val instance: Data by lazy { DataHolder.INSTANCE }
    }

    private val contactList = mutableListOf<Contact>()
    private val subscribers = mutableListOf<Observer>()
    override fun addSubscriber(subscriber: Observer) {
        if (!subscribers.contains(subscriber)) subscribers.add(subscriber)
    }

    override fun removeSubscriber(subscriber: Observer) {
        subscribers.remove(subscriber)
    }

    override fun notifySubscribers(position: Int, operation: Int) {
        subscribers.forEach { subscriber: Observer -> subscriber.notify(position, operation) }
    }

    fun addContact(contact: Contact) {
        if (!contactList.contains(contact)) contactList.add(contact)
    }

    fun removeContact(contact: Contact) {
        contactList.remove(contact)
    }

    fun setContact(position: Int, contact: Contact) {
        contactList[position] = contact
    }
}