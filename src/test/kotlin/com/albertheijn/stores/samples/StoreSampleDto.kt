package com.albertheijn.stores.samples

import com.albertheijn.stores.dto.AddressDto
import com.albertheijn.stores.dto.AddressPeriodDto
import com.albertheijn.stores.dto.StoreDto
import java.time.LocalDate
import java.time.LocalDateTime


val storeCreatedAt = LocalDateTime.now()
val storeUpdatedAt = LocalDateTime.now()

val address = AddressDto(
    "Van Baerlestraat",
    33,
    "a",
    1071,
    "AP Amsterdam",
    "Netherlands"
)
val addPeriod = AddressPeriodDto(
    LocalDate.of(2018,10,12),
    null,
    address
)
val storeSample = StoreDto(
    1001,
    "AH Van Baerlestraat",
    "Active",
    storeCreatedAt,
    storeUpdatedAt,
    listOf(addPeriod)
)

val InvalidAddP = AddressPeriodDto(
    LocalDate.of(2018,10,12),
    LocalDate.of(2017,10,12),
    address
)
val InvalidStore = StoreDto(
    1001,
    "AH Van Baerlestraat",
    "Active",
    storeCreatedAt,
    storeUpdatedAt,
    listOf(InvalidAddP)
)
val notFoundStore =StoreDto(
    1003,
    "AH Van Baerlestraat",
    "Active",
    storeCreatedAt,
    storeUpdatedAt,
    listOf(addPeriod)
)