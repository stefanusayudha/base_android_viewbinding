package com.iddevops.core.common.data

import androidx.annotation.NonNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createRetrofitService(
    @NonNull service: Class<T>,
    httpClient: OkHttpClient,
    baseUrl: String
): T = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(service)