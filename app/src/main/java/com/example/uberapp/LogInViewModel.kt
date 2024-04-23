package com.example.uberapp

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow

class LogInViewModel: ViewModel() {

    val pickedCountryCode = MutableStateFlow<String>("+7")




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
