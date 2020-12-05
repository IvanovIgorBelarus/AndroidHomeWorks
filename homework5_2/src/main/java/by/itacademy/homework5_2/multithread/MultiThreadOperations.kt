package by.itacademy.homework5_2.multithread

import by.itacademy.homework5_2.data.Contact

interface MultiThreadOperations {
    fun changeContact(changeContactsListener: ChangeContactsListener, contact: Contact, position: Int)
    fun getUsersFromDB(usersListListener: UsersListListener)
    fun removeContact(changeContactsListener: ChangeContactsListener, position: Int)
    fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact)
}