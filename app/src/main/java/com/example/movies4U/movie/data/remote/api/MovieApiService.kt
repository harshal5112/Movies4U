package com.example.movies4U.movie.data.remote.api

import com.example.movies4U.BuildConfig
import com.example.movies4U.movie.data.remote.models.MovieDto
import com.example.movies4U.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovies(
        @Query("api_key") apiKey: String= BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String= BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto



}

