package com.android.app.domain

sealed class Result<T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val message: Throwable) : Result<T>()

}