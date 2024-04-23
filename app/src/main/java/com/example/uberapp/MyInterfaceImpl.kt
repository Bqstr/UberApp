package com.example.uberapp

class MyInterfaceImpl(val myApi: MyApi):MyInterface {
    override suspend fun getPersons():List<Persons> {
        return myApi.getPersons()
    }
}