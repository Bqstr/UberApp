package com.example.uberapp

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel(val myRepository:MyInterface): ViewModel() {


    var pickedCountryCode = MutableStateFlow<String>("+7")

    val list_from_search =MutableStateFlow<List<String>>(listOf(""))


    var list_data = MutableStateFlow<List<Persons>>(listOf(Persons(1,"123",1)))



    fun getPersons(){
        Log.d("abobus","aa")

        viewModelScope.launch {
            Log.d("abobus","a")


            val retrofit = Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            Log.d("abobus","a")


            val myApi = retrofit.create(MyApi::class.java)
            Log.d("abobus","a")

            val s =myApi.getPersons()
             list_data.value =s
            Log.d("12213213321312",s.toString())

            //list.value =resp


        }
    }


    fun getDeviceLocation(fusedLocationProviderClient: FusedLocationProviderClient) {

    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getAdress(adres:String, context: Context) {
        val geoCoder = Geocoder(context)
        viewModelScope.launch(Dispatchers.IO) {
            var list_adress = mutableListOf<String>()

            if (adres != "") {
                val locas = geoCoder.getFromLocationName(adres, 5,) {loca->

                    if (!loca.isEmpty()) {
                        for (a in loca) {
                            Log.d("dsdsdssd", "Place # ${adres}")

                            Log.d("dsdsdssd", "phone: ${(loca[0] as Address).locale}")
                            Log.d("dsdsdssd", "featureName: ${(loca[0] as Address).featureName}")
                            Log.d("dsdsdssd", "premises: ${(loca[0] as Address).premises}")
                            Log.d("dsdsdssd", "subAdminArea: ${(loca[0] as Address).subAdminArea}")
                            Log.d("dsdsdssd", "subLocality: ${(loca[0] as Address).subLocality}")
                            Log.d("dsdsdssd", "thoroughfare: ${(loca[0] as Address).thoroughfare}")
                            Log.d("dsdsdssd", "locality: ${(loca[0] as Address).locality}")
                            Log.d("dsdsdssd", "adminArea: ${(loca[0] as Address).adminArea}")
                            Log.d(
                                "dsdsdssd",
                                "adminArea: ${(loca[0] as Address).latitude},${(loca[0] as Address).longitude}"
                            )
                            Log.d("dsdsdssd", "adminArea: ${(loca[0] as Address).latitude}")


                            list_adress.add("${(a as Address).thoroughfare} ,${(a as Address).featureName}" )
                            list_from_search.value =list_adress
                            Log.d("sdaadsasdsadsad", list_from_search.value.toString())





                        }


                    } else {
                        list_from_search.value = listOf()

                        Log.d("dsdsdssd", "xhto")

                    }
                }


            }
            //list_from_search.value =list_adress
            Log.d("sdaadsasdsadsad  at the end",list_from_search.value.toString())
            //list_from_search.value =list


            }
        Log.d("sdfjkhdsjlkf", "$list_from_search")

        }




    companion object{
        fun provideFactory(
            // cityRepository: CityRepository,
            //  weatherRepository: WeatherRepository,
            myRepository: MyInterface,

            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return MyViewModel(
                        //   cityRepository,weatherRepository,
                        myRepository
                    ) as T
                }
            }
    }


}