package com.example.assessment.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assessment.data.network.WeatherNetwork
import com.example.assessment.ui.DetailsViewModel
import com.example.assessment.ui.WeatherViewModel
import com.example.assessment.ui.theme.DarkBlue

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    connected: Boolean,
    navController: NavHostController
) {
    val context = LocalContext.current

    val internetConnection = remember {connected}

    LaunchedEffect(key1 = Unit, ) {
        if (internetConnection) {
            Toast.makeText(
                context,
                "Good Network Connection!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                context,
                "Network Connection Lost",
                Toast.LENGTH_LONG
            ).show()


        }
    }

        LazyColumn(
        ) {
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }

            item {
                Button(onClick = { navController.navigate(Routes.HOME.name) }) {
                    Text(text = "Go to Syncronous Loading")
                }
            }

            items(viewModel.netResultList) {
                WeatherCard2(results = it)
            }

        }



}

@Composable
fun WeatherCard2(results: WeatherNetwork) {
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = DarkBlue,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = "City: ", fontWeight = FontWeight.Bold)
            Text(text = results.location.name, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Divider()
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier
            .padding(10.dp)) {
            Text(text = "Temperature: ", fontWeight = FontWeight.Bold)
            Text(text = results.current.temp_f + "°F", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Divider()
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier
            .padding(10.dp)) {
            Text(text = "Wind Speed: ", fontWeight = FontWeight.Bold)
            Text(text = results.current.wind_kph + "kph", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Divider()
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier
            .padding(10.dp)) {
            Text(text = "Conditions: ", fontWeight = FontWeight.Bold)
            Text(text = results.current.condition.text, fontWeight = FontWeight.Bold)
        }
    }
}