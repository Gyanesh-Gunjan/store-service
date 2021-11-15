package com.example.storeservice.data.entity

data class StoreAddress(
    val street : String ,
    val houseNumber : String,
    val houseNumberSuffix : String,
    val postalCode : String,
    val city : String,
    val country : String
)
