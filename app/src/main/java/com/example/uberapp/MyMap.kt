package com.example.uberapp


import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.example.uberapp.R
import com.example.uberapp.utils.bitmapDescriptor
import com.example.uberapp.utils.calculateDistance
import com.example.uberapp.utils.calculateSurfaceArea
import com.example.uberapp.utils.capitaliseIt
import com.example.uberapp.utils.formattedValue

import android.location.Address;
import android.location.Geocoder;
import android.location.Geocoder.GeocodeListener
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.TextField
import androidx.lifecycle.viewmodel.viewModelFactory


@Composable
fun MyMap(
    context: Context,
    latLng: LatLng,
    changeIcon: Boolean = false,
    lineType: LineType? = null,
    mapProperties: MapProperties = MapProperties(),
    onChangeMarkerIcon: () -> Unit,
    onChangeMapType: (mapType: MapType) -> Unit,
    onChangeLineType: (lineType: LineType?) -> Unit,

    ) {

    Log.d("sdfkjhkdsfaklsfd",latLng.latitude.toString())
    val latlangList = remember {
        mutableStateListOf(latLng)
    }
    var mapTypeMenuExpanded by remember { mutableStateOf(false) }
    var mapTypeMenuSelectedText by remember {
        mutableStateOf(
            MapType.NORMAL.name.capitaliseIt()
        )
    }
    var lineTypeMenuExpanded by remember { mutableStateOf(false) }
    var lineTypeMenuSelectedText by remember {
        mutableStateOf(
            lineType?.name?.capitaliseIt() ?: "Line Type"
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 15f)
    }
    val locaation = remember{mutableStateOf("")}


    val routeCoordinates = listOf(
        LatLng(43.271397799999995, 76.9521937), // Start location of the first leg
        LatLng(43.2539442, 76.9325391), // End location of the first leg
        LatLng(43.271397799999995, 76.9521937), // Start location of the second leg
        LatLng(43.2728864, 76.9520533), // End location of the second leg
        LatLng(43.2729796, 76.9531295), // End location of the second leg (from NAME_CHANGE step)
        LatLng(43.271170999999995, 76.95333839999999), // End location of the third leg
        LatLng(43.271170999999995, 76.95333839999999), // Start location of the fourth leg
        LatLng(43.2697217, 76.93438929999999), // End location of the fourth leg
        LatLng(43.2697217, 76.93438929999999), // Start location of the fifth leg
        LatLng(43.2558635, 76.9361205), // End location of the fifth leg
        LatLng(43.2558635, 76.9361205), // Start location of the sixth leg
        LatLng(43.255556999999996, 76.9308696), // End location of the sixth leg
        LatLng(43.255556999999996, 76.9308696), // Start location of the seventh leg
        LatLng(43.2538846, 76.93104129999999), // End location of the seventh leg
        LatLng(43.2538846, 76.93104129999999), // Start location of the eighth leg
        LatLng(43.2539442, 76.9325391), // End location of the eighth leg
        LatLng(43.2539442, 76.9325391), // Start location of the ninth leg
        LatLng(43.2538846, 76.93104129999999), // End location of the ninth leg
        LatLng(43.2538846, 76.93104129999999), // Start location of the tenth leg
        LatLng(43.2539442, 76.9325391) // End location of the tenth leg
    )

    val geoCoder =Geocoder(context)

    Box(modifier = Modifier.fillMaxSize()) {




        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            onMapClick = {
                if (lineType == null) {
                    latlangList.add(it)
                }
            }
        ) {
            Polyline(
                points = routeCoordinates,
                clickable = true,
                color = Color.Blue,
                width = 5f,
                tag = "MY_Route",
                onClick = {
                }
            )








            latlangList.toList().forEach {
                Marker(
                    state = MarkerState(position = it),
                    title = "Location",
                    snippet = "Marker in current location",
                    icon = if (changeIcon) {
                        bitmapDescriptor(context, R.drawable.google_icon)
                    } else null
                )
            }

            if (lineType == LineType.POLYLINE) {
                Polyline(points = latlangList, color = Color.Red)
            }
            if (lineType == LineType.POLYGON) {
                Polygon(
                    points = latlangList,
                    fillColor = Color.Green,
                    strokeColor = Color.Red,
                    strokeWidth = 2F
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            Button(onClick = onChangeMarkerIcon) {
                Text(text = if (changeIcon) "Default Marker" else "Custom Marker")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Row {
                Button(onClick = { mapTypeMenuExpanded = true }) {
                    Text(text = mapTypeMenuSelectedText)
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
                DropdownMenu(expanded = mapTypeMenuExpanded,
                    onDismissRequest = { mapTypeMenuExpanded = false }) {
                    MapType.values().forEach {
                        val mapType = it.name.capitaliseIt()
                        DropdownMenuItem(text = {
                            Text(text = mapType)
                        }, onClick = {
                            onChangeMapType(it)
                            mapTypeMenuSelectedText = mapType
                            mapTypeMenuExpanded = false
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            if (latlangList.size > 1) {
                Row {
                    Button(onClick = { lineTypeMenuExpanded = true }) {
                        Text(text = lineTypeMenuSelectedText)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown arrow",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                    DropdownMenu(expanded = lineTypeMenuExpanded,
                        onDismissRequest = { lineTypeMenuExpanded = false }) {
                        val lineTypes = LineType.values().toMutableList()
                        if (latlangList.size < 3) {
                            lineTypes.remove(LineType.POLYGON)
                        }
                        lineTypes.forEach {
                            val lineType = it.name.capitaliseIt()
                            DropdownMenuItem(text = {
                                Text(text = lineType)
                            }, onClick = {
                                onChangeLineType(it)
                                lineTypeMenuSelectedText = lineType
                                lineTypeMenuExpanded = false
                            })
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(onClick = {
                        onChangeLineType(null)
                        latlangList.clear()
                        latlangList.add(latLng)
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                        Text(text = "Clear", color = Color.White)
                    }
                }
            }
        }

        if (lineType != null) {
            Text(
                text = when (lineType) {
                    LineType.POLYLINE -> "Total distance: ${
                        formattedValue(
                            calculateDistance(latlangList)
                        )
                    } km"

                    LineType.POLYGON -> "Total surface area: ${
                        formattedValue(
                            calculateSurfaceArea(latlangList)
                        )
                    } sq. mtrs"
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.White)
                    .padding(8.dp),
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SearchBar( viewModel: MyViewModel, context: Context){
    val locaation = remember{mutableStateOf("")}
    Box(Modifier.fillMaxSize()) {

        TextField(value = locaation.value, onValueChange = {
            locaation.value =it


            viewModel.getAdress(it,context)




        }, modifier = Modifier.align(Alignment.BottomCenter))
    }
}