package by.itacademy.homework9.presentation

import android.annotation.SuppressLint
import android.content.Context
import by.itacademy.homework9.data.db.CityRepository
import by.itacademy.homework9.data.db.CityRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers

class CityActivityPresenterImpl(
        context: Context,
        private val cityRepository: CityRepository = CityRepositoryImpl(context),
        private val cityListener: CityListener = CityListenerImpl(context.getSharedPreferences(CITY, Context.MODE_PRIVATE))
) : CityActivityPresenter {
    @SuppressLint("CheckResult")
    override fun updateAdapter(adapter: CityAdapter) {
        cityRepository.getCitiesFromDb()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { adapter.update(it) }
    }

    override fun setCity(name: String) {
        cityListener.saveCity(name)
    }

}