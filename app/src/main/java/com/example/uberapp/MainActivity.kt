package com.example.uberapp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.uberapp.screens.ConfirmNumberScreen.Companion.ConfirmNumberr
import com.example.uberapp.screens.ConfirmNumberScreen.Companion.Confirm_NumberScreen
import com.example.uberapp.screens.ConfirmNumberScreen.Companion.DecoratedTextField
import com.example.uberapp.screens.LogInScreen.Companion.LogIn_Screen
import com.example.uberapp.screens.MainPageScreen

import com.example.uberapp.ui.theme.UberAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val viewModel: MyViewModel by viewModels {
        MyViewModel.provideFactory(
            // App.cityRepository,
            //App.weatherRepository,
            App.apiRepository,
            this
        )
    }




    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.getDeviceLocation(fusedLocationProviderClient)

            }
        }

    private fun askPermissions() = when {
        ContextCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            viewModel.getDeviceLocation(fusedLocationProviderClient)
        }

        else -> {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }



    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        askPermissions()

        val s = R.drawable.strelochka

        setContent {
            UberAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {


                    MainPageScreen.mainPage()

                    //ConfirmNumberr("+7784266401")
                    //DecoratedTextField( boxHeight = 76.dp, boxWidth = 76.dp,    value = sss.value, length = 4, onValueChange ={ sss.value =it} )
                    //ConfirmNumber()
                    //mainPage()
                    //SearchBar(viewModel,applicationContext)
                    //MapScreen(applicationContext ,)
                   //SearchScreen(viewModel,applicationContext)
                   // LogIn_Screen(viewModel)


                    //ApiCall
//                        Column() {
//                            Text(text = viewModel.list.collectAsState().toString(), fontSize = 20.sp)
//
//                            Button(
//                                onClick = {
//                                    viewModel.getPersons().toString()
//                                }
//                            ) {
//                                Text("Button")
//                            }
//                        }
                }
            }
        }
    }










    @Composable
    fun LaunchScreen(name: String, modifier: Modifier = Modifier) {
        Box(
            modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Text(
                text = "Uber",
                modifier = modifier.align(Alignment.Center),
                fontSize = 24.sp
            )
        }
    }





    //    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize(Alignment.TopEnd)
//    ) {
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                imageVector = Icons.Default.MoreVert,
//                contentDescription = "More"
//            )
//        }












    @Preview
    @Composable
    fun prev() {
        Surface {

            //LocationPermissionScreen()
        }
    }
}










































