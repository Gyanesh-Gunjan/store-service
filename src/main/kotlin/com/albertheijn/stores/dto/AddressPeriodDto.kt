package com.albertheijn.stores.dto

import java.time.LocalDate

data class AddressPeriodDto(
    val dateValidFrom: LocalDate,
    val dateValidUntil: LocalDate?,
    val addressDto: AddressDto
)
