package com.example.assessment.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.assessment.data.network.WeatherNetwork
import com.example.assessment.ui.WeatherViewModel
import com.example.assessment.ui.theme.DarkBlue


@Composable
fun HomeScreen(
    viewModel: WeatherViewModel
) {
    

    LazyColumn(
    ) {
        item{
            Spacer(modifier = Modifier.height(70.dp))
        }
        
        items(viewModel.netResultList) {
            WeatherCard(results = it)
        }
    }


}

@Composable
fun WeatherCard(results: WeatherNetwork) {
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
