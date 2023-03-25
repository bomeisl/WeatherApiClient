package com.example.assessment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.assessment.ui.theme.AssessmentTheme
import com.example.assessment.ui.views.HostScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            fun checkForInternet(context: Context): Boolean {

                // register activity with the connectivity manager service
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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


            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }

                    HostScreen(connected = checkForInternet(this))
                }

                }
            }
        }







