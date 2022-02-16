package com.albertheijn.stores.dto

data class AddressDto(
    val street: String,
    val houseNumber: Int,
    val houseNumberSuffix: String,
    val postalCode: Long,
    val city: String,
    val country: String
)
