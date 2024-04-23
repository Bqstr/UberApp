package com.example.uberapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uberapp.R

class ConfirmNumberScreen {
    companion object {



        @Composable
        fun ConfirmNumberr(phoneNumber: String){
            var sss =remember{ mutableStateOf("") }

            Box() {
                Column(Modifier.padding(start =20.dp,end =20.dp)){
                Text(
                    fontSize = 14.sp,
                    text = "Enter the 4-digit code sent you at",
                    modifier = Modifier.padding(top = 20.dp)
                )
                Text(
                    fontSize = 20.sp,
                    text ="$phoneNumber",fontWeight = FontWeight.Bold
                ,modifier =Modifier.padding(bottom = 20.dp)
                )
                DecoratedTextField( boxHeight = 70.dp, boxWidth = 70.dp,    value = sss.value, length = 4, onValueChange ={ sss.value =it} )
                    Button(
                        modifier = Modifier.padding(top = 20.dp),
                        onClick = { },
                        shape = RoundedCornerShape(1000.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffeeeeee)),
                    ) {
                        Text("I didn’t recive a code", color =Color.Black)
                    }

                    Button(
                        modifier = Modifier.padding(top = 6.dp),

                        onClick = { },
                        shape = RoundedCornerShape(1000.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffeeeeee)),
                    ) {

                        Text("Login with password",color =Color.Black)


                    }
                }








                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
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
                        modifier = Modifier
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
        fun Confirm_NumberScreen(phoneNumber: String) {
            Box(Modifier.fillMaxSize()) {
                Column() {
                    Text(
                        text = "Enter the 4-digit code sent you at $phoneNumber",
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Row(Modifier.padding(top = 20.dp)) {
                        repeat(4) {
                            Box(
                                modifier = Modifier
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
                        modifier = Modifier
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
                        modifier = Modifier
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
        fun DecoratedTextField(
            value: String,
            length: Int,
            modifier: Modifier = Modifier,
            boxWidth: Dp = 38.dp,
            boxHeight: Dp = 38.dp,
            enabled: Boolean = true,
            keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions: KeyboardActions = KeyboardActions(),
            onValueChange: (String) -> Unit,
        ) {
            val spaceBetweenBoxes = 8.dp
            BasicTextField(modifier = modifier,
                value = value,
                singleLine = true,
                onValueChange = {
                    if (it.length <= length) {
                        onValueChange(it)
                    }
                },
                enabled = enabled,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                decorationBox = {
                    Row(
                        Modifier.size(
                            width = (boxWidth + spaceBetweenBoxes) * length,
                            height = boxHeight
                        ),
                        horizontalArrangement = Arrangement.spacedBy(spaceBetweenBoxes),
                    ) {
                        repeat(length) { index ->
                            Box(
                                modifier = Modifier
                                    .size(boxWidth, boxHeight)
                                    .border(
                                        1.dp,
                                        color = Color.Red,
                                        shape = RoundedCornerShape(4.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = value.getOrNull(index)?.toString() ?: "",
                                    textAlign = TextAlign.Center,
                                    //style = MaterialTheme.typography.h6
                                )
                            }
                        }
                    }
                })
        }


    }
//    @Preview
//    @Composable
//    fun preview(){
//        Surface() {
//            //Confirm_NumberScreen("+77784266401")
//            DecoratedTextField(value = "", length = 4, onValueChange ={} )
//        }
//    }

}