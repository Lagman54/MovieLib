package com.example.finalproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.finalproject.common.Const.Api.MOVIES_PAGE_SIZE
import com.example.finalproject.data.MovieApi
import com.example.finalproject.data.data_source.MoviePagingSource
import com.example.finalproject.data.mapper.mapToDomain
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video
import com.example.finalproject.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getMovies(1).movies.map(MovieEntity::mapToDomain)
    }

    override suspend fun getMovie(id: Int): MovieDetails {
        return api.getMovieDetails(id).mapToDomain()
    }

    override suspend fun getVideos(id: Int): List<Video> {
        return api.getMovieVideos(id).videos.map(VideoEntity::mapToDomain)
    }

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                maxSize = 200
            ),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow
            .map { pagingData ->
                pagingData.map(MovieEntity::mapToDomain)
            }
    }

}