package com.example.assessment

import com.example.assessment.data.network.Condition
import com.example.assessment.data.network.Current
import com.example.assessment.data.network.Location
import com.example.assessment.data.network.WeatherApi
import com.example.assessment.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class NetworkUnitTest {

    private val mockServer = MockWebServer()

    val baseUrl: HttpUrl = mockServer.url("/weather.json/")

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(mockServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)


    @Test
    fun testRetrofitClient() {
        mockServer.enqueue(MockResponse().setBody("{\n" +
                "    \"location\": {\n" +
                "        \"name\": \"London\",\n" +
                "        \"region\": \"City of London, Greater London\",\n" +
                "        \"country\": \"United Kingdom\",\n" +
                "        \"lat\": 51.52,\n" +
                "        \"lon\": -0.11,\n" +
                "        \"tz_id\": \"Europe/London\",\n" +
                "        \"localtime_epoch\": 1679694005,\n" +
                "        \"localtime\": \"2023-03-24 21:40\"\n" +
                "    },\n" +
                "    \"current\": {\n" +
                "        \"last_updated_epoch\": 1679693400,\n" +
                "        \"last_updated\": \"2023-03-24 21:30\",\n" +
                "        \"temp_c\": 8.0,\n" +
                "        \"temp_f\": 46.4,\n" +
                "        \"is_day\": 0,\n" +
                "        \"condition\": {\n" +
                "            \"text\": \"Partly cloudy\",\n" +
                "            \"icon\": \"//cdn.weatherapi.com/weather/64x64/night/116.png\",\n" +
                "            \"code\": 1003\n" +
                "        },\n" +
                "        \"wind_mph\": 11.9,\n" +
                "        \"wind_kph\": 19.1,\n" +
                "        \"wind_degree\": 230,\n" +
                "        \"wind_dir\": \"SW\",\n" +
                "        \"pressure_mb\": 998.0,\n" +
                "        \"pressure_in\": 29.47,\n" +
                "        \"precip_mm\": 0.1,\n" +
                "        \"precip_in\": 0.0,\n" +
                "        \"humidity\": 87,\n" +
                "        \"cloud\": 25,\n" +
                "        \"feelslike_c\": 4.4,\n" +
                "        \"feelslike_f\": 39.9,\n" +
                "        \"vis_km\": 10.0,\n" +
                "        \"vis_miles\": 6.0,\n" +
                "        \"uv\": 1.0,\n" +
                "        \"gust_mph\": 22.8,\n" +
                "        \"gust_kph\": 36.7\n" +
                "    }\n" +
                "}"))


        runBlocking {
            val response = retrofit.getWeather("")

            val actual = WeatherNetwork(
                Location("London"),
                Current("46.4", "19.1", Condition("Partly cloudy"))
            )

            assertEquals(response, actual)
        }




    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun resetCoroutines() {
        Dispatchers.resetMain()
    }

}
