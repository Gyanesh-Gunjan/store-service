package com.albertheijn.stores

import com.albertheijn.stores.model.Address
import com.albertheijn.stores.model.AddressPeriod
import com.albertheijn.stores.model.Store
import java.time.LocalDate
import java.time.LocalDateTime

val createdAt = LocalDateTime.now()
val updatedAt = LocalDateTime.now()

val ad1 = Address(
    "Van Baerlestraat",
    33,
    "a",
    1071,
    "AP Amsterdam",
    "Netherlands"
)

val adp1 = AddressPeriod(
    LocalDate.of(2018,10,12),
    null,
    ad1
)

val adp2 = AddressPeriod(
    LocalDate.of(2018,10,12),
    LocalDate.of(2023,10,12),
    ad1
)

val adp3 = AddressPeriod(
    LocalDate.of(2025,10,12),
    LocalDate.of(2050,10,12),
    ad1
)

val sampleStore1 = Store(
    1001,
    "AH Van Baerlestraat",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp1)
)