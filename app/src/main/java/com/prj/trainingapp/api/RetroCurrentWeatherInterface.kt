package com.prj.trainingapp.api

import com.prj.trainingapp.model.CurrentWeatherData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroCurrentWeatherInterface {

    @GET("data/2.5/weather?")
    fun getCurrentByCityName(@Query("q")cityName: String,
                             @Query("appid")apiKey: String,
                             @Query("units")units : String = "metric"): Call<CurrentWeatherData>

    @GET("data/2.5/weather?")
    fun getCurrentByLatLon(@Query("lat")lat: String,
                           @Query("lon")lon: String,
                           @Query("appid")apiKey: String,
                           @Query("units")units : String = "metric"): Call<CurrentWeatherData>

    @GET("data/2.5/weather?")
    fun getCurrentByZipCode(@Query("zip")zipCode: String,
                           @Query("appid")apiKey: String,
                            @Query("units")units : String = "metric"): Call<CurrentWeatherData>

    companion object{
         val baseUrl: String ="http://api.openweathermap.org/"
    }

}

class RetroCurrentWeatherApi{

    fun initialize() : RetroCurrentWeatherInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(RetroCurrentWeatherInterface.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetroCurrentWeatherInterface::class.java)
    }

}