package com.example.movies4U.ui.navigation

import com.example.movies4U.utils.K

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()
    data class FilmScreen(
        val route: String = "FilmScreen",
        val routeWithArgs: String = "$route/{${K.MOVIE_ID}}",
    ) : Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}