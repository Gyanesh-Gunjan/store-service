package com.storeservice.data.model

data class StoreAddress(
    var street: String,
    var houseNumber: Int,
    var houseNumberSuffix: String,
    var postalCode: Long,
    var city: String,
    var country: String
)

//var storeAddres = StoreAddress(
//    "Kings street",
//    234,
//    "4M",
//    746579,
//    "oningsstraat",
//    "Neitherland"
//)