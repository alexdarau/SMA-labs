package upt.ac.lab2.data.database

import androidx.room.RoomDatabase
import androidx.room.Database


@Database(entities = [ChiuitEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chiuitDao(): ChiuitDao
}