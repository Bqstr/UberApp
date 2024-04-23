package com.example.uberapp.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberapp.R
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.filled.AccessTimeFilled

class MainPageScreen {

companion object {

    @Composable
    fun mainPage() {
        Box(Modifier.padding(start = 20.dp, end = 20.dp)) {


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
                        .height(170.dp)
                        .fillMaxWidth()

                ) {

                    Column(
                        modifier = Modifier.align(
                            Alignment.CenterStart
                        )
                    ) {
                        Text(
                            text = "Satisfy any carving",
                            fontSize = 21.sp,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(text = "Order on Eats ->", fontSize = 15.sp)

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
                PickupSelection(Modifier ,{})









                Text(text = "Around you", modifier = Modifier.padding(top =10.dp), fontSize = 14.sp)

            }
        }
    }

    @Composable
    fun ButtonOnMainPage(text: String, onCLick: () -> Unit, icon: ImageVector) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onCLick }
        ) {
            Row(Modifier.align(Alignment.CenterStart)) {
                Box {
                    Circle()
                    Image(
                        icon,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )


                }

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




    @Composable
    fun Circle() {
        Canvas(modifier = Modifier.size(38.dp), onDraw = {
            drawCircle(color = Color.Gray)
        })
    }


    @Composable
    fun PickupSelection(modifier: Modifier, onGotoWhereScreen: () -> Unit) {
        Row(
            modifier = modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color.Gray)
                .clickable {
                    onGotoWhereScreen()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1.5f)
                    .padding(start = 30.dp)
                    .background(color = Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = "Enter pickup point",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f)
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(color = MaterialTheme.colorScheme.onPrimary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTimeFilled,
                    contentDescription = "timer Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Now",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "drop down Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }




}


    @Preview
    @Composable
    fun preview(){
        Surface {
            //Confirm_NumberScreen("+77784266401")
            //mainPage()
            //DestinationSelection(Modifier ,{})
        }
    }



}