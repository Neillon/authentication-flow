package com.neillon.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neillon.persistence.dao.UserDao

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app_database")
                    // TODO: Get all migrations on migrations package and add them
                    // .addMigrations()
                    .build()
            }
            return instance
        }
    }

}