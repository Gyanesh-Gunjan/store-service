package com.albertheijn.stores.model

import java.time.LocalDate

data class AddressPeriod(
    val dateValidFrom: LocalDate,
    val dateValidUntil: LocalDate?,
    val storeAddress: Address
)
