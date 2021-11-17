package com.storeservice.data.model

import java.util.*
import javax.persistence.*

@Entity
data class StoreDetails(
    @Id
    var Id : Long,
    var name: String,
    var status: String,
    var createdAt: Date,
    var updatedAt: Date,
    @OneToMany(cascade = [CascadeType.ALL])
    var addressPeriod: List<AddressPeriod>
)