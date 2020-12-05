package by.itacademy.homework5_2.multithread

import android.content.Context
import by.itacademy.homework5_2.data.Contact
import by.itacademy.homework5_2.data.DBOperations
import by.itacademy.homework5_2.data.DBOperationsImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class RXJavaHelper(context: Context) : MultiThreadOperations {
    private val dbOperations: DBOperations = DBOperationsImpl(context)
    override fun changeContact(changeContactsListener: ChangeContactsListener, contact: Contact, position: Int) {

    }

    override fun getUsersFromDB(usersListListener: UsersListListener) {
        Observable.just( dbOperations.getUsersFromDB() )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { usersListListener.getUsersList(it) }

    }

    override fun removeContact(changeContactsListener: ChangeContactsListener, position: Int) {
        TODO("Not yet implemented")
    }

    override fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact) {
        TODO("Not yet implemented")
    }

}