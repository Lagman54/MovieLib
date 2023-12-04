package com.example.finalproject.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@Entity(tableName = "watch_list")
data class WatchListMovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("movie_id")
    val movie_id: Int,
    @ColumnInfo("is_favorite")
    val is_favorite: Boolean,
    @ColumnInfo("poster_path")
    val posterPath: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("genre")
    val genre: String
)
