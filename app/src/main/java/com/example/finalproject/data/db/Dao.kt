package com.example.finalproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: WatchListMovieEntity)

    @Query("DELETE FROM watch_list WHERE movie_id=:id")
    fun remove(id: Int)

    @Query("SELECT * FROM watch_list")
    fun getMoviesFlow(): Flow<List<WatchListMovieEntity>>

    @Query("SELECT * FROM watch_list WHERE movie_id=:id")
    fun getMovie(id: Int): WatchListMovieEntity?

}