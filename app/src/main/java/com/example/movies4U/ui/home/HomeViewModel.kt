package com.example.movies4U.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies4U.movie.domain.models.Movie
import com.example.movies4U.movie.domain.repository.MovieRepository
import com.example.movies4U.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,

):ViewModel(){
    private val _homeState = MutableStateFlow(HomeState())
    val homeState =_homeState.asStateFlow()

    init {
        fetchDiscoverMovies()
    }
    init {
        fetchTrendingMovies()
    }

    private fun fetchDiscoverMovies()= viewModelScope.launch{
        repository.fetchDiscoverMovies().collectAndHandle(
            onError = {error->
                _homeState.update {
                    it.copy(isLoading=false,error= error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading=true,error = null)
                }
            }

        ){movie ->
            _homeState.update {
                it.copy(isLoading=false,error=null,discoverMovies = movie )
            }
        }
}
    private fun fetchTrendingMovies()= viewModelScope.launch{
        repository.fetchTrendingMovies().collectAndHandle(
            onError = {error->
                _homeState.update {
                    it.copy(isLoading=false,error= error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading=true,error = null)
                }
            }

        ){movie ->
            _homeState.update {
                it.copy(isLoading=false,error=null,trendingMovies = movie )
            }
        }
}
data class HomeState(
    val discoverMovies: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
){
}
}
