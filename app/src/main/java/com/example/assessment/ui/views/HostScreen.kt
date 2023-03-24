package com.example.assessment.ui.views

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assessment.ui.WeatherViewModel

enum class Routes {
    HOME, DETAILS
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HostScreen() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val navHostController: NavHostController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Weather")})},
        content = {
            NavHost(
                navController = navHostController,
                startDestination = Routes.HOME.name
            ) {

                composable(Routes.HOME.name)
                { HomeScreen(viewModel = weatherViewModel) }



            }
        }
    )


}