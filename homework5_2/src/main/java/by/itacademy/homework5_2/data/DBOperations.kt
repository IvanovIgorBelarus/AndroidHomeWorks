package by.itacademy.homework5_2.data

interface DBOperations {
    fun changeContact(contact: Contact, position: Int)
    fun getUsersFromDB(): List<Contact>
    fun removeContact(position: Int)
    fun saveContactInDB(contact: Contact)
}