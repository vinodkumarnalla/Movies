package com.android.app.data.mappers

interface BaseMapper<DATA, MODEL> {
    suspend fun transform(data: DATA): MODEL
}