package by.itacademy.homework5_2.multithread

import by.itacademy.homework5_2.data.Contact

interface UsersListListener {
    fun getUsersList(list: List<Contact>)
}