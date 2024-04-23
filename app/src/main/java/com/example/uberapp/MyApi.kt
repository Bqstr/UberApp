package com.example.uberapp

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    companion object{
        const val BASE_URL ="https://ex-api-production.up.railway.app/"
    }




    @GET("persons")
    suspend fun getPersons():List<Persons>
}

data class Persons(
    @SerializedName("id")
    val id:Int ,
    @SerializedName("name")
    val name:String ,
    @SerializedName("age")
    val age:Int
)