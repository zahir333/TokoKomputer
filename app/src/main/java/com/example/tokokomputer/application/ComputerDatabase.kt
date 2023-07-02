package com.example.tokokomputer.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokokomputer.dao.ComputerDao
import com.example.tokokomputer.model.Computer

@Database(entities = [Computer::class], version = 1, exportSchema = false)
abstract class ComputerDatabase:RoomDatabase() {
    abstract fun computerDao(): ComputerDao

    companion object {
        private var INSTANCE: ComputerDatabase? = null
        fun getDatabase(context: Context): ComputerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ComputerDatabase::class.java,
                    "computer_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}