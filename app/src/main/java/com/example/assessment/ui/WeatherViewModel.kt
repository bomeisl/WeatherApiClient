package com.example.assessment.ui

import android.content.Context
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.data.network.Condition
import com.example.assessment.data.network.Current
import com.example.assessment.data.network.Location
import com.example.assessment.data.network.WeatherApi
import com.example.assessment.data.network.WeatherNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi
    ): ViewModel() {

    var internet = NET_CAPABILITY_INTERNET

    val errorResponse = WeatherNetwork(
        Location("City not Found"),
        Current("data not found", "data not found", Condition("Data not Found"))
    )

    var result1 = MutableStateFlow<WeatherNetwork>(WeatherNetwork())
    var result2 = MutableStateFlow<WeatherNetwork>(WeatherNetwork())
    var result3 = MutableStateFlow<WeatherNetwork>(WeatherNetwork())
    var result4 = MutableStateFlow<WeatherNetwork>(WeatherNetwork())
    var result5 = MutableStateFlow<WeatherNetwork>(WeatherNetwork())

    fun getNetworkData() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                result1.value = weatherApi.getWeather("current.json?key=520916eb3f46442ca1c12926221402&q=visakhapatnam")
            } catch (e: HttpException) {
                result1.value = errorResponse
            }
            try {
                result2.value = weatherApi.getWeather("current.json?key=520916eb3f46442ca1c12926221402&q=london")
            } catch (e: HttpException) {
                result2.value = errorResponse
            }
            try {
                result3.value = weatherApi.getWeather("current.json?key=520916eb3f46442ca1c12926221402&q=camden")
            } catch (e: HttpException) {
                result3.value = errorResponse
            }
            try {
                result4.value = weatherApi.getWeather("current.json?key=520916eb3f46442ca1c12926221402&q=dublin")
            } catch (e: HttpException) {
                result4.value = errorResponse
            }
            try {
                result5.value = weatherApi.getWeather("current.json?key=520916eb3f46442ca1c12926221402&q=cairo")
            } catch (e: HttpException) {
                result5.value = errorResponse
            }
            }
    }

    init {
        getNetworkData()
    }

    private fun checkForInternet(context: viewModelCon): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}


}