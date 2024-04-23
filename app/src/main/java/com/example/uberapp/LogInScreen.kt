package com.example.uberapp

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.asStateFlow


class LogInScreen {

    companion object {


        @Composable
        fun LogIn_Screen(viewModel: MyViewModel) {

            var pickedCountryCode by rememberSaveable {
                mutableStateOf("+7")
            }
            Surface {


                Column {

                    Row(){
                        CountryCodePick(
                            Modifier
                                .width(100.dp)
                                .height(60.dp),pickedCountryCode,viewModel)
                        PhonePick(Modifier.height(60.dp),pickedCountryCode ,viewModel)
                    }

                    Text("By continuing you may recive an SMS fo rverification. Message and data rates may apply")

                    //Image(painter = , contentDescription = )


                    //StraightLine()
                    BlackLineWithText("or")

                }
            }


        }


        @Composable
        fun BlackLineWithText(text: String) {
            Row( // Use Row for horizontal layout
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Spacer(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(4.dp)
                        .align(Alignment.CenterVertically)
                ) // Spacer for centering
                Text(
                    text = text,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Spacer(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(4.dp)
                        .align(Alignment.CenterVertically)
                ) // Spacer for centering
            }
        }


        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun CountryCodePick(modifier: Modifier,pickedCountryCode:String,viewModel:MyViewModel) {
            //val number_options = listOf<String>("KAZ +7", "USA  +1", " UZB  +997 ")
            val countryPhoneCodes = listOf(
                "United States" to "+1",
                "Canada" to "+1",
                "United Kingdom" to "+44",
                "Australia" to "+61",
                "Germany" to "+49",
                "Kazakhstan" to "+7",
                "Uzbekistan" to "+997"
            )

            var selected_option by remember { mutableStateOf("Kazakhstan:+7") }
            var expanded by remember { mutableStateOf(false) }


            Column(

            ) {

                ExposedDropdownMenuBox(

                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        modifier =modifier.menuAnchor(),

                        readOnly = true,

                        value = selected_option,
                        onValueChange = {


                        },
                        label = { },

                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        // colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(Color.Black)
                    )
                    ExposedDropdownMenu(
                        modifier =Modifier.width(250.dp),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        countryPhoneCodes.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = "${text.first}${text.second}") },
                                onClick = {
                                    selected_option =
                                        "${countryPhoneCodes[index].first}:${countryPhoneCodes[index].second}"
                                    expanded = false
                                    Log.d("12312213132123132","changed")
                                    viewModel.pickedCountryCode.value ="+9"

                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                            )
                        }


                    }


                }
            }


        }


        @Composable
        fun PhonePick(modifier: Modifier, pickedCountryCode:String,viewModel: MyViewModel) {
            var phoneNumber by rememberSaveable { mutableStateOf("") }
            Column(modifier) {
                PhoneField(phoneNumber,
                    mask = "${viewModel.pickedCountryCode.collectAsState().value}-000-000-00-00",
                    maskNumber = '0',
                    onPhoneChanged = { phoneNumber = it })
            }
        }

        @Composable
        fun PhoneField(
            phone: String,
            modifier: Modifier = Modifier,
            mask: String = "000 000 00 00",
            maskNumber: Char = '0',
            onPhoneChanged: (String) -> Unit
        ) {
            TextField(
                value = phone,
                onValueChange = { it ->
                    onPhoneChanged(it.take(mask.count { it == maskNumber }))
                },
                label = {
                    Text(text = "Phone number")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = PhoneVisualTransformation(mask, maskNumber),
                modifier = modifier.fillMaxWidth(),
            )
        }

        class PhoneVisualTransformation(val mask: String, val maskNumber: Char) :
            VisualTransformation {

            private val maxLength = mask.count { it == maskNumber }

            override fun filter(text: AnnotatedString): TransformedText {
                val trimmed = if (text.length > maxLength) text.take(maxLength) else text

                val annotatedString = buildAnnotatedString {
                    if (trimmed.isEmpty()) return@buildAnnotatedString

                    var maskIndex = 0
                    var textIndex = 0
                    while (textIndex < trimmed.length && maskIndex < mask.length) {
                        if (mask[maskIndex] != maskNumber) {
                            val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                            append(mask.substring(maskIndex, nextDigitIndex))
                            maskIndex = nextDigitIndex
                        }
                        append(trimmed[textIndex++])
                        maskIndex++
                    }
                }

                return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
//            if (other !is PhonedVisualTransformation) return false
//            if (mask != other.mask) return false
//            if (maskNumber != other.maskNumber) return false
                return true
            }

            override fun hashCode(): Int {
                return mask.hashCode()
            }
        }

        private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                var noneDigitCount = 0
                var i = 0
                while (i < offset + noneDigitCount) {
                    if (mask[i++] != numberChar) noneDigitCount++
                }
                return offset + noneDigitCount
            }

            override fun transformedToOriginal(offset: Int): Int =
                offset - mask.take(offset).count { it != numberChar }
        }

        @Composable
        @Preview
        fun LogInScreenPreview() {
            Surface() {
                Box(Modifier.fillMaxSize()) {



                }
            }


        }


    }
}