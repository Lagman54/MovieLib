package com.example.finalproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.finalproject.common.Const.Api.MOVIES_PAGE_SIZE
import com.example.finalproject.common.Const.Api.TRAILER
import com.example.finalproject.data.network.api.MovieApi
import com.example.finalproject.data.db.WatchListMovieDao
import com.example.finalproject.data.db.WatchListMovieEntity
import com.example.finalproject.data.network.data_source.MoviePagingSource
import com.example.finalproject.data.network.data_source.SearchMoviePagingSource
import com.example.finalproject.data.mapper.mapToDomain
import com.example.finalproject.data.mapper.mapToTrailer
import com.example.finalproject.data.mapper.mapToWatchListEntity
import com.example.finalproject.data.network.model.MovieEntity
import com.example.finalproject.data.network.model.VideoEntity
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Trailer
import com.example.finalproject.domain.model.Video
import com.example.finalproject.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val watchListMovieDao: WatchListMovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getMovies(1).movies.map(MovieEntity::mapToDomain)
    }

    override suspend fun getSimilarMovies(id: Int): List<Movie> {
        return api.getSimilarMovies(id, 1).movies.map(MovieEntity::mapToDomain)
    }

    override suspend fun getMovie(id: Int): MovieDetails {
        return api.getMovieDetails(id).mapToDomain()
    }

    override suspend fun getVideos(id: Int): List<Video> {
        return api.getMovieVideos(id).videos.map(VideoEntity::mapToDomain)
    }

    override suspend fun getTrailers(id: Int): List<Trailer> {
        return api.getMovieVideos(id).videos.filter { it.type == TRAILER }
            .map(VideoEntity::mapToTrailer)
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

    override fun getMoviesByTitle(title: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                maxSize = 200
            ),
            pagingSourceFactory = { SearchMoviePagingSource(api, title) }
        ).flow
            .map { pagingData ->
                pagingData.map(MovieEntity::mapToDomain)
            }
    }

    override suspend fun getNowPlayingMovies(): List<Movie> {
        return api.getNowPlayingMovies(1).movies.map(MovieEntity::mapToDomain)
    }

    override fun getWatchListMovies(): Flow<List<Movie>> {
        return watchListMovieDao.getMoviesFlow().map { data ->
            data.map(WatchListMovieEntity::mapToDomain)
        }
    }

    override fun addToWatchList(movie: Movie) {
        watchListMovieDao.insert(movie.mapToWatchListEntity())
    }

    override fun inWatchList(id: Int): Boolean = watchListMovieDao.getMovie(id) != null

    override fun removeFromWatchList(id: Int) {
        watchListMovieDao.remove(id)
    }

}