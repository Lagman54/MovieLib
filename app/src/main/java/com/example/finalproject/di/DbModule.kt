package com.example.finalproject.di

import android.app.Application
import androidx.room.Room
import com.example.finalproject.common.Const.DB.DB_NAME
import com.example.finalproject.data.db.AppDataBase
import com.example.finalproject.data.db.WatchListMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDb(app: Application): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideWatchListMovieDao(db: AppDataBase): WatchListMovieDao = db.watchListMovieDao()

}