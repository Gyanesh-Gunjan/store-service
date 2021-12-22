package com.AlbertHeijn.storeservice.validation

import com.AlbertHeijn.storeservice.model.AddressPeriod
import com.AlbertHeijn.storeservice.model.StoreDetails
import java.time.LocalDate
import java.time.LocalDateTime

fun IsValidStore(storeDetails: StoreDetails) : Boolean =
    IsValidAddressPeriod(storeDetails.addressPeriod) &&
            IsValidCreateUpdateDate(storeDetails.createdAt, storeDetails.updatedAt)


fun IsValidAddressPeriod(addressPeriod : List<AddressPeriod>) : Boolean {
    addressPeriod.forEach{it ->
        val validStatus = when{
            it.dateValidFrom >= LocalDate.now()
                    && ((it.dateValidUntil == null) || (it.dateValidUntil!! >= it.dateValidFrom) ) -> true
            else -> false
        }
        if(!validStatus) return false
    }
    return true
}


fun IsValidCreateUpdateDate(createdAt : LocalDateTime, updatedAt : LocalDateTime) : Boolean = when {
        createdAt <= LocalDateTime.now() && updatedAt >= createdAt ->  true
        else ->  false
    }



