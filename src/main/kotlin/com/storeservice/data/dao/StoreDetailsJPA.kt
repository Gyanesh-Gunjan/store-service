package com.storeservice.data.dao
import com.storeservice.data.model.StoreDetails
import org.springframework.data.jpa.repository.JpaRepository

interface StoreDetailsJPA : JpaRepository<StoreDetails, Long> {
}