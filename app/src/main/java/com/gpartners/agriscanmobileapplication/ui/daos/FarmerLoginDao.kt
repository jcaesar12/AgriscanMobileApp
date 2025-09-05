package com.gpartners.agriscanmobileapplication.ui.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gpartners.agriscanmobileapplication.ui.FarmerRegistration
import retrofit2.http.Query

@Dao
interface FarmerLoginDao {
    @Query("SELECT * FROM FarmerRegistration WHERE username = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): FarmerRegistration?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFarmer(farmer: FarmerRegistration)
}
