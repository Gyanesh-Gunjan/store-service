package com.albertheijn.stores.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "StoreDB")
data class Store(
    @Id
    val storeId: Long,
    val name: String,
    val status: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    var addressPeriod: List<AddressPeriod>
)
