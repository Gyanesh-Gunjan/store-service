package com.example.storeservice.data.entity
import java.util.*

data class Stores(
    val Id : Int,
    val name : String,
    val status : String,
    val createdAt : Date,
    val updatedAt : Date,
    val addressPeriod : List<AddressPeriod>
)
