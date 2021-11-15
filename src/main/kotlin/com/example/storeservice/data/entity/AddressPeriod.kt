package com.example.storeservice.data.entity

import java.util.*

data class AddressPeriod(
    val dataValidFrom : String,
    val dateValidUntil : String,
    val storesList : List<StoreAddress>
)
