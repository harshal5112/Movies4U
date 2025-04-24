package com.example.movies4U.movie_detail.domain.repository

import com.example.movies4U.movie.domain.models.Movie
import com.example.movies4U.movie_detail.domain.models.MovieDetail
import kotlinx.coroutines.flow.Flow
import com.example.movies4U.utils.Response

interface MovieDetailRepository {
    fun fetchMovieDetail(movieid: Int): Flow<Response<MovieDetail>>
    fun fetchMovie(): Flow<Response<List<Movie>>>

}