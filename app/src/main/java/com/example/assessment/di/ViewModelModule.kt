package com.example.assessment.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.assessment.data.network.WeatherApi
import com.example.assessment.ui.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object ViewModelModule {

    @Provides
    fun ProvidesViewModel(
        weatherApi: WeatherApi): WeatherViewModel {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                WeatherViewModel(
                    weatherApi
                )
            }
        }
        return Factory.create(WeatherViewModel::class.java)
    }

}