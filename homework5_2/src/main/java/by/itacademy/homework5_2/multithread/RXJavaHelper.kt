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
        Observable.just( dbOperations.changeContact(contact,position) )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { changeContactsListener.changeContact(contact.name) }
    }

    override fun getUsersFromDB(usersListListener: UsersListListener) {
        Observable.just( dbOperations.getUsersFromDB() )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { usersListListener.getUsersList(it) }

    }

    override fun removeContact(changeContactsListener: ChangeContactsListener, position: Int) {
        Observable.just( dbOperations.removeContact(position) )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  changeContactsListener.removeContact() }
    }

    override fun saveContactInDB(saveContactsListener: SaveContactsListener, contact: Contact) {
        Observable.just( dbOperations.saveContactInDB(contact) )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {   saveContactsListener.saveContactInDB(contact.name) }
    }

}