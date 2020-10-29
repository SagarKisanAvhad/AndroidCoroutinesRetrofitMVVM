package com.devtides.androidcoroutinesretrofit.repository

import com.devtides.androidcoroutinesretrofit.model.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://raw.githubusercontent.com/DevTides/"

interface CountryApi {
    @GET("countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>

    companion object {
        operator fun invoke(): CountryApi {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(CountryApi::class.java)
        }
    }
}