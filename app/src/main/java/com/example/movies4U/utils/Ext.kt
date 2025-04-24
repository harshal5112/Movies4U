package com.example.movies4U.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<Response<T>>.collectAndHandle(
    onError: (Throwable?)-> Unit = {
        Log.e("collectAndHandle", "collectAndHandle:")
    },
    onLoading:()-> Unit = {},
    stateReducer:(T)->Unit,
){
    collect { response ->
        when (response) {
            is Response.Error -> {
                onError(response.error)
            }
            is Response.Loading -> {
                onLoading()
            }
            is Response.Success ->{
            stateReducer(response.data)
            }
        }
    }
}