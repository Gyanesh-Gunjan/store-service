package com.AlbertHeijn.storeservice.model

data class StoreAddress(
    var street: String,
    var houseNumber: Int,
    var houseNumberSuffix: String,
    var postalCode: Long,
    var city: String,
    var country: String
)