package com.albertheijn.stores.dto

import java.time.LocalDateTime

data class StoreDto(
    val storeId: Long,
    val name: String,
    val status: String,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    val addressPeriodDto: List<AddressPeriodDto>
)

