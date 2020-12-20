package by.itacademy.homework9mvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface CityDao {
    @Query("SELECT * FROM Cities")
    fun getCities(): Flowable<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)
}