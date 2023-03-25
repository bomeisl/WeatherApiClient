package com.example.assessment.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessment.data.network.Condition
import com.example.assessment.data.network.Current
import com.example.assessment.data.network.Location
import com.example.assessment.data.network.WeatherApi
import com.example.assessment.data.network.WeatherNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val weatherApi: WeatherApi
): ViewModel() {

    val errorResponse = WeatherNetwork(
        Location("City not Found"),
        Current("data not found", "data not found", Condition("Data not Found"))
    )

    var netResultList = mutableStateListOf<WeatherNetwork>(

    )

    val cityList = listOf<String>(
        "Bangalore",
        "London",
        "Camden",
        "Dublin",
        "Cairo"
    )

    suspend fun call(city: String): WeatherNetwork {
        val result = viewModelScope.async {
            try {
                return@async weatherApi.getWeather("${urlPrefix}=${key}&q=${city}")
            } catch (e: Exception) {
                return@async errorResponse
            }
        }
        return result.await()
    }


    fun getNetworkData() {
        viewModelScope.launch {
            for (i in 0..cityList.size - 1) {
                netResultList += call(cityList[i])
            }
        }
    }

    init {
        getNetworkData()
    }



    companion object {
        val key = "520916eb3f46442ca1c12926221402"
        val urlPrefix = "current.json?key"
    }

}