package by.itacademy.homework5_2.multithread

import android.content.Context
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperations
import by.itacademy.homework5_2.data.DBOperationsImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

class CompletableFutureHelper(private val context: Context) : MultiThreadOperations {
    private val dbOperations: DBOperations = DBOperationsImpl(context)
    override fun changeContact(changeContactsListener: ChangeContactsListener, contact: Contact, position: Int) {
        CompletableFuture.supplyAsync(Supplier {
            dbOperations.changeContact(contact, position)
        }, context.mainExecutor).thenAcceptAsync {
            changeContactsListener.changeContact(contact.name)
        }
    }

    override fun getUsersFromDB(usersListListener: UsersListListener) {
        CompletableFuture.supplyAsync(Supplier {
            dbOperations.getUsersFromDB()
        }, context.mainExecutor).thenAcceptAsync {
            usersListListener.getUsersList(it)
        }
    }

    override fun removeContact(changeContactsListener: ChangeContactsListener, position: Int) {
        CompletableFuture.supplyAsync(Supplier {
            dbOperations.removeContact(position)
        }, context.mainExecutor).thenAcceptAsync {
            changeContactsListener.removeContact()
        }
    }

    override fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact) {
        CompletableFuture.supplyAsync(Supplier {
            dbOperations.saveContactInDB(contact)
        }, context.mainExecutor).thenAcceptAsync {
            saveContactsListener.saveContactInDB(contact.name)
        }
    }
}