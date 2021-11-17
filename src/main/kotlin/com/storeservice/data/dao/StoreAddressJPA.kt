package com.storeservice.data.dao

import com.storeservice.data.model.AddressPeriod
import org.springframework.data.jpa.repository.JpaRepository

interface StoreAddressJPA : JpaRepository<AddressPeriod, Long> {
}