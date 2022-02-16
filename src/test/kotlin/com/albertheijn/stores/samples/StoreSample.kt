package com.albertheijn.stores.samples


import com.albertheijn.stores.mapper.StoreMapper
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
val sampleStore2 = Store(
    1002,
    "AH Van",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp1, adp2, adp3)
)
val sampleStore3 = Store(
    1002,
    "AH Van",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp1, adp2, adp3)
)
val sampleStore4 = Store(
    1001,
    "AH Van Baerlestraat",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp1)
)
val storeDB = listOf(sampleStore1, sampleStore2)
val storeDB1 = listOf(sampleStore4, sampleStore3)

// for default ref date and future flag = false
val storesList = listOf(sampleStore1, Store(
    1002,
    "AH Van",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp1, adp2)
))
val storesListExp = storesList.map { StoreMapper().toDto(it)}

// for default refDate and future flag = true
val exp = listOf(Store(
    1002,
    "AH Van",
    "Active",
    createdAt,
    updatedAt,
    listOf(adp3)
))
val expResult_FF = exp.map { StoreMapper().toDto(it) }