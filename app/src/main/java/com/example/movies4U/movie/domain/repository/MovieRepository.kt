package com.example.movies4U.movie.domain.repository

import com.example.movies4U.movie.domain.models.Movie
import com.example.movies4U.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovies(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovies(): Flow<Response<List<Movie>>>
}