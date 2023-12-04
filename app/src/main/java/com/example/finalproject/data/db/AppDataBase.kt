package com.example.finalproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalproject.common.Const.DB.DB_VERSION

@Database(
    entities = [
        WatchListMovieEntity::class
    ],
    version = DB_VERSION
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun watchListMovieDao(): WatchListMovieDao
}