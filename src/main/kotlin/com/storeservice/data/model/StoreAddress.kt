package com.storeservice.data.model

import javax.persistence.*

@Entity
data class StoreAddress(
    var street: String,
    var houseNumber: Int,
    var houseNumberSuffix: String,
    var postalCode: Int,
    var city: String,
    var country: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var Id: Long? = null
}