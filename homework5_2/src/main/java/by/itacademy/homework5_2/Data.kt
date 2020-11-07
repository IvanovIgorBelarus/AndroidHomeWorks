package by.itacademy.homework5_2

class Data private constructor() : Observable {
    private object DataHolder {
        val INSTANCE = Data()
    }

    companion object {
        val instance: Data by lazy { DataHolder.INSTANCE }
    }

    val CHANGE = 1
    val REMOVE = 2
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

    fun getContacts() = contactList

    fun addContact(contact: Contact) {
        if (!contactList.contains(contact)) contactList.add(contact)
    }

    fun removeContact(position: Int) {
        contactList.remove(contactList[position])
        notifySubscribers(position, REMOVE)
    }

    fun setContact(position: Int, contact: Contact) {
        contactList[position] = contact
        notifySubscribers(position, CHANGE)
    }
}