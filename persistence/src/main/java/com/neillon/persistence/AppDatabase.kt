package com.neillon.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neillon.persistence.dao.UserDao
import com.neillon.persistence.entity.UserEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [UserEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app_database")
                    // TODO: Get all migrations on migrations package and add them
                    // .addMigrations()
                    .build()
            }
            return instance!!
        }
    }

}