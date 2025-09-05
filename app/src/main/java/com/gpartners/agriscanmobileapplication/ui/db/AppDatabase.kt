package com.gpartners.agriscanmobileapplication.ui.db

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gpartners.agriscanmobileapplication.ui.FarmerRegistration
import com.gpartners.agriscanmobileapplication.ui.daos.FarmerLoginDao

@Database(entities = [FarmerRegistration::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun farmerRegistrationDao(): FarmerLoginDao
}
