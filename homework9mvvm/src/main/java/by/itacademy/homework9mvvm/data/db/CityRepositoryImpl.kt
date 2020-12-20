package by.itacademy.homework9mvvm.data.db

import android.annotation.SuppressLint
import android.content.Context
import by.itacademy.homework9mvvm.data.Weather
import by.itacademy.homework9mvvm.model.CityModelMapper
import io.reactivex.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CityRepositoryImpl(context: Context) : CityRepository {
    private val cityDao: CityDao = CitiesRoomDatabase.getDatabase(context).getCityDao()
    private val cityModelMapper: CityModelMapper = CityModelMapper()

    @SuppressLint("CheckResult")
    override fun getCitiesFromDb(): Flowable<List<City>> = cityDao.getCities()

    override fun insertCityInDb(weather: Weather) {
        Observable.just(cityDao)
                .subscribeOn(Schedulers.io())
                .subscribe { it.insert(cityModelMapper.invoke(weather)) }
    }

}