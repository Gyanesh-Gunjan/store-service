package com.AlbertHeijn.storeservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class StoreDetails(
    @Id
    var storeId: Long,
    var name: String,
    var status: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var addressPeriod: List<AddressPeriod>
)