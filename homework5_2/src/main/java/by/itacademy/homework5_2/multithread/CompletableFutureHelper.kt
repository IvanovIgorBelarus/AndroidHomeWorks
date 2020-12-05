package by.itacademy.homework5_2.multithread

import android.content.Context
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperations
import by.itacademy.homework5_2.data.DBOperationsImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

class CompletableFutureHelper(private val context: Context):MultiThreadOperations {
    private val dbOperations: DBOperations = DBOperationsImpl(context)
    override fun changeContact(changeContactsListener: ChangeContactsListener, contact: Contact, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getUsersFromDB(usersListListener: UsersListListener) {
        CompletableFuture.supplyAsync {
            dbOperations.getUsersFromDB()
        }.thenRunAsync(Runnable {  },context.mainExecutor)
    }

    override fun removeContact(changeContactsListener: ChangeContactsListener, position: Int) {
        TODO("Not yet implemented")
    }

    override fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact) {
        TODO("Not yet implemented")
    }
}