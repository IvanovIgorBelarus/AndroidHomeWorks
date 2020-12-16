package by.itacademy.homework9.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM Cities")
    fun getCities(): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)
}