package com.example.assessment.data.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url


data class WeatherNetwork(
    @SerializedName("location") val location: Location = Location(),
    @SerializedName("current") val current: Current = Current()
    )

data class Location (
    @SerializedName("name") val name: String = ""
    )

data class Current (
    @SerializedName("temp_f") val temp_f: String = "",
    @SerializedName("wind_kph") val wind_kph: String = "",
    @SerializedName("condition") val condition: Condition = Condition(),
    )

data class Condition (
    @SerializedName("text") val text: String = ""
    )




interface WeatherApi {
    @GET
    suspend fun getWeather(@Url city: String) : WeatherNetwork
}

