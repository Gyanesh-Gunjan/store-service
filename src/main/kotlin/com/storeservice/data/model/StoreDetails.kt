package com.storeservice.data.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.util.*

@Document
data class StoreDetails(
    @Id
    var storeId : Long,
    var name: String,
    var status: String,
    var createdAt: Date,
    var updatedAt: Date,
    var addressPeriod: List<AddressPeriod>
)