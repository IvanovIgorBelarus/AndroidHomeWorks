package by.itacademy.homework5_2.multithread

import android.content.Context
import android.os.Handler
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperations
import by.itacademy.homework5_2.data.DBOperationsImpl
import java.util.concurrent.Executors

class ThreadPoolHelper(private val context: Context) : MultiThreadOperations {
    private val handler = Handler(context.mainLooper)
    private val executor = Executors.newFixedThreadPool(10)
    private val dbOperations: DBOperations = DBOperationsImpl(context)
    override fun changeContact(changeContactsListener: ChangeContactsListener, contact: Contact, position: Int) {
        executor.submit(Runnable {
            dbOperations.changeContact(contact, position)
            handler.post(Runnable { changeContactsListener.changeContact("${contact.name}") })
        })
    }

    override fun getUsersFromDB(usersListListener: UsersListListener) {
        executor.submit(Runnable {
            val result = dbOperations.getUsersFromDB()
            handler.post(Runnable { usersListListener.getUsersList(result) })
        })
    }

    override fun removeContact(changeContactsListener: ChangeContactsListener, position: Int) {
        executor.submit(Runnable {
            dbOperations.removeContact(position)
            handler.post(Runnable { changeContactsListener.removeContact() })
        })
    }

    override fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact) {
        executor.submit(Runnable {
            dbOperations.saveContactInDB(contact)
            handler.post(Runnable { saveContactsListener.saveContactInDB("${contact.name}") })
        })
    }
}
