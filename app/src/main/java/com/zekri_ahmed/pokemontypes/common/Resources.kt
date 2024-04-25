package com.zekri_ahmed.pokemontypes.common

sealed class Resources<T>(
    val data: T? = null,
    val message: String = "",
    val warning: String = "",
    val loading: Boolean = false
) {
    class Success<T>(data: T?, warning: String = "") : Resources<T>(data = data, warning = warning)
    class Error<T>(error: String) : Resources<T>(message = error)
    class Loading<T> : Resources<T>(loading = true)
}