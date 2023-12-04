package com.example.finalproject.data.network.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.finalproject.common.Const.Api.MOVIE_STARTING_PAGE_INDEX
import com.example.finalproject.data.network.api.MovieApi
import com.example.finalproject.data.network.model.MovieEntity
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: MovieApi
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val position = params.key ?: MOVIE_STARTING_PAGE_INDEX
            val response = api.getMovies(position)
            val movies = response.movies
            LoadResult.Page(
                data = movies,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}