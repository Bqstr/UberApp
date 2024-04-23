package com.example.uberapp

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object App {



    val retrofit = Retrofit.Builder()
        .baseUrl(MyApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


     val myApi = retrofit.create(MyApi::class.java)



    val apiRepository:MyInterface by lazy{
        MyInterfaceImpl(myApi)
    }





}