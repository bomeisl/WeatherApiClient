Weather API Client

* MVVM 

* Clean Architecture 

* Fully Hilt dependency injected 

* Retrofit network calls to the web interface at weatherapi.com to retrieve weather data 

* Network Call error and exception handling - will display "data not found" and "city not found" to user if incorrect city is passed as a parameter

* Unit Testing of network calls via OkHttp Mock Server to test JSON deserialization and the app's Retrofit Client

* Network Connectivity Monitoring - when network connectivity is stable, a toast is displayed saying "Good Network Connection!" or else when the network
connection is lost a toast is displayed saying "Network Connection Lost"

* Kotlin Coroutines allow both asyncronous query and syncronous query of data from weatherapi.com API endpoint.

Architecture Outline:

|Data Layer|

I. Network Layer: 
Sets up the Data Class modeling of the JSON structure and guide for GSON to deserialize to Kotlin Data Class Objects. Defines the API call interface as well
which takes a single string argument to represent the city for which the weather data is to be queried. The Retrofit Client and other network components
are injected by Hilt where needed.

|UI Layer|

I. View Model
Makes asyncronous calls to the web API endpoint at weatherapi.com for 5 different cities. Fully dependency injected by Hilt, error and exception handling: in case the correct data is not returned by the Retrofit network call for any city, "city not found" is diplayed for the name of the city,
and "data not found" is displayed for each field. 

II. Compose UI
Scaffolded Host Screen with Jetpack Compose-Navigation containing the Landing Screen with a LazyColumn displaying the weather data for the selected cities.

