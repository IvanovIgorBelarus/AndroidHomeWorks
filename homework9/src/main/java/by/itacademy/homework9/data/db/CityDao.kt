package by.itacademy.homework9.data.db

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

    @Query("SELECT * FROM Cities WHERE city = :name")
    fun getCity(name: String): City

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)
}