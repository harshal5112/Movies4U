package com.example.movies4U.movie.data.repository_impl

import android.util.Log.e
import com.example.movies4U.common.data.ApiMapper
import com.example.movies4U.movie.data.remote.api.MovieApiService
import com.example.movies4U.movie.data.remote.models.MovieDto
import com.example.movies4U.movie.domain.models.Movie
import com.example.movies4U.movie.domain.repository.MovieRepository
import com.example.movies4U.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
):MovieRepository {
   override fun fetchDiscoverMovies(): Flow<Response<List<Movie>>> = flow {
       emit(Response.Loading())
       val movieDto = movieApiService.fetchDiscoverMovies()
       apiMapper.mapToDomain(movieDto).apply {
           emit(Response.Success(this))
       }
   }.catch {e->
       emit(Response.Error(e))
   }
   override fun fetchTrendingMovies(): Flow<Response<List<Movie>>> = flow {
       emit(Response.Loading())
       val movieDto = movieApiService.fetchTrendingMovies()
       apiMapper.mapToDomain(movieDto).apply {
           emit(Response.Success(this))
       }
   }.catch {e->
       emit(Response.Error(e))
   }
}