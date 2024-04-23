@file:JvmName("MapScreenKt")

package com.example.uberapp
import android.Manifest
import com.google.android.gms.location.Priority
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uberapp.utils.getCurrentLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.MapProperties
import kotlinx.coroutines.flow.asStateFlow


lateinit var fusedLocationProviderClient: FusedLocationProviderClient


        @Composable
        fun MapScreen(context: Context) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)


            var showMap by remember { mutableStateOf(false) }
            var location by remember { mutableStateOf(LatLng(0.0, 0.0)) }
            var mapProperties by remember { mutableStateOf(MapProperties()) }
            var changeIcon by remember { mutableStateOf(false) }
            var lineType by remember {
                mutableStateOf<LineType?>(null)
            }

            getCurrentLocation(context ) {
                location = it
                showMap = true
            }



            getCurrentLocation(context, {
                Log.d("HEY", "Yes")

            }, {

                Log.d("HEY", "NO")

            })


            //requestLocationUpdates(context)

            if (showMap) {
                MyMap(
                    context = context,
                    latLng = location,
                    mapProperties = mapProperties,
                    lineType = lineType,
                    changeIcon = changeIcon,
                    onChangeMarkerIcon = {
                        changeIcon = !changeIcon
                    },
                    onChangeMapType = {
                        mapProperties = mapProperties.copy(mapType = it)
                    }, onChangeLineType = {
                        lineType = it
                    })
            } else {
                Text(text = "Loading Map...")
            }


        }


        @SuppressLint("MissingPermission")
        fun getCurrentLocation(
            context: Context,
            onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
            onGetCurrentLocationFailed: (Exception) -> Unit,
            priority: Boolean = true
        ) {
            Log.d("123123123123", "EEEEEEEEEEEEEEEEEEEEEEEEEEEE")

            // Determine the accuracy priority based on the 'priority' parameter
            val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
            else Priority.PRIORITY_BALANCED_POWER_ACCURACY

            // Check if location permissions are granted
            if (areLocationPermissionsGranted(context)) {
                Log.d("123123123123", "Granted")

                // Retrieve the current location asynchronously
                fusedLocationProviderClient.getCurrentLocation(
                    accuracy, CancellationTokenSource().token,
                ).addOnSuccessListener { location ->
                    location?.let {
                        Log.d("123123123123", "Abobus")


                        // If location is not null, invoke the success callback with latitude and longitude
                        onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
                    }
                }.addOnFailureListener { exception ->
                    Log.d("123123123123", "Failier")

                    // If an error occurs, invoke the failure callback with the exception
                    onGetCurrentLocationFailed(exception)
                }
            }
        }


        private fun requestLocationUpdates(context: Context) {

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {


                return
            }

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    Log.d("fdsjkaljkdsfal", location?.longitude.toString())

                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle failure

                }


        }


        private fun areLocationPermissionsGranted(context: Context): Boolean {
            return (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED)


        }

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: MyViewModel,context:Context){
    var text by remember{ mutableStateOf("")}
    var active_bar by remember{ mutableStateOf(false)}
    //var items = mutableListOf("Abobus","1231231")
    var itemss = viewModel.list_from_search.asStateFlow().value
        // viewModel.getAdress(text, context)


    val tint =MaterialTheme.colorScheme.onSurface
    androidx.compose.material3.SearchBar(
        modifier =Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            viewModel.getAdress(it,context)
            text =it
        },
        onSearch = {},
        active = active_bar,
        onActiveChange = {active_bar =it},
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon ={
            Icon(imageVector = Icons.Default.Search ,contentDescription = null)
        },
        trailingIcon = {
            if(active_bar){
                Icon(modifier =Modifier.clickable { text ="" },imageVector = Icons.Default.Close,contentDescription = null)

            }
        }
        ,

    ) {



        itemss.forEach{
            Row(Modifier.padding(all =14.dp)){
                Text(text = it)
            }
        }
    }
}



