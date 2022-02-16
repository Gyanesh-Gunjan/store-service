package com.albertheijn.stores.model

data class Address(
    val street: String,
    val houseNumber: Int,
    val houseNumberSuffix: String,
    val postalCode: Long,
    val city: String,
    val country: String
)
