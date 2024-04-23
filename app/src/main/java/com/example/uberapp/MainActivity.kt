package com.example.uberapp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.uberapp.LogInScreen.Companion.LogIn_Screen

import com.example.uberapp.ui.theme.UberAppTheme
import com.example.uberapp.utils.checkForPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.asStateFlow


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

                    //ConfirmNumber()
                    //mainPage()
                    //SearchBar(viewModel,applicationContext)
                    //MapScreen(applicationContext ,)
                   //SearchScreen(viewModel,applicationContext)
                    LogIn_Screen(viewModel)


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
    fun mainPage() {
        Box(Modifier.padding(start = 10.dp, end = 10.dp)) {


            Column() {

                Image(
                    painter = painterResource(id = R.drawable.image_7),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .align(Alignment.End)
                        .width(50.dp)
                        .height(50.dp)
                        .padding(top = 4.dp, end = 8.dp, bottom = 6.dp)

                )
                Box(
                    Modifier
                        .background(Color(0xFF10462E), MaterialTheme.shapes.medium)
                        .height(120.dp)
                        .fillMaxWidth()

                ) {

                    Column(
                        modifier = Modifier.align(
                            Alignment.CenterStart
                        )
                    ) {
                        Text(text = "Satisfy any carving")
                        Text(text = "Order on Eats ->")

                    }
                    Image(
                        painter = painterResource(id = R.drawable.image_8),
                        contentDescription = "image",
                        modifier = Modifier
                            .align(
                                Alignment.CenterEnd
                            )
                            .fillMaxHeight()
                            .padding(top = 10.dp)
                    )

                }


                ButtonOnMainPage(
                    text = "hoose a saved place",
                    onCLick = { },
                    icon = Icons.Default.Star
                )
                ButtonOnMainPage(
                    text = "Choose a saved place",
                    onCLick = { },
                    icon = Icons.Default.LocationOn
                )








                Text(text = "Around you")

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


    @Composable
    fun LoginScreen() {
        Row() {
        }
//        PhoneField(phoneNumber,
//            mask = "+7-000-000-00-00",
//            maskNumber = '0',
//            onPhoneChanged = { phoneNumber = it })
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



    @Composable
    fun StraightLine() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Define the starting and ending points of the line
            val startX = size.width * 0.1f // Adjust as needed
            val startY = size.height / 2f
            val endX = size.width * 0.9f // Adjust as needed
            val endY = size.height / 2f

            // Draw the line
            drawLine(
                color = Color.Black, // Adjust color as needed
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 5f, // Adjust thickness as needed
                cap = StrokeCap.Round // Adjust stroke cap as needed
            )
        }
    }




    @Composable
    fun ConfirmNumber(modifier: Modifier = Modifier) {
        val code = "123212323"
        Box() {
            Column() {
                Text(
                    text = "Enter the 4-digit code sent you at $code",
                    modifier = modifier.padding(top = 20.dp)
                )

                Row(modifier.padding(top = 20.dp)) {
                    repeat(4) {
                        Box(
                            modifier = modifier
                                .padding(start = 20.dp)
                                .requiredWidth(width = 54.dp)
                                .requiredHeight(height = 49.dp)
                                .background(
                                    color = Color(0xffeeeeee)


                                )
                        )
                    }
                }

                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = { },
                    shape = RoundedCornerShape(1000.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffeeeeee)),
                ) {
                    Text("I didn’t recive a code")
                }

                Button(
                    modifier = Modifier.padding(top = 2.dp),

                    onClick = { },
                    shape = RoundedCornerShape(1000.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffeeeeee)),
                ) {

                    Text("Login with password",)


                }
            }








            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp, bottom = 12.dp)
                        .requiredSize(size = 49.dp)
                        .clip(shape = CircleShape)
                        .background(
                            Color(0xffeeeeee), shape = CircleShape

                        )
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(14.dp, 14.dp),
                        painter = painterResource(id = R.drawable.strelochka),
                        contentDescription = "back button",

                        )
                }


                Box(
                    modifier = modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp, bottom = 12.dp)
                        .requiredSize(size = 49.dp)
                        .clip(shape = CircleShape)
                        .background(
                            Color(0xffeeeeee), shape = CircleShape

                        )
                ) {


                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(14.dp, 14.dp)
                            .rotate(180f),
                        painter = painterResource(id = R.drawable.strelochka),
                        contentDescription = "back button",

                        )
                }


            }
        }


    }

    @Composable
    fun ButtonOnMainPage(text: String, onCLick: () -> Unit, icon: ImageVector) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onCLick }
        ) {
            Row(Modifier.align(Alignment.CenterStart)) {
                Image(
                    icon,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(text, modifier = Modifier.align(Alignment.CenterVertically))
            }
            Image(
                Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )

        }
        Image(painterResource(id = R.drawable.vector_4), contentDescription = null)
    }


    @Preview
    @Composable
    fun prev() {
        Surface {

            //LocationPermissionScreen()
        }
    }
}










































