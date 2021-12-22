package com.AlbertHeijn.storeservice.dao

import com.AlbertHeijn.storeservice.model.StoreDetails
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface StoreDetailsRepo : MongoRepository<StoreDetails, Long> {
    fun findByName(name : String) : Optional<StoreDetails>
//    fun findByAddressPeriodDateValidFromLessThan(refDate: LocalDate?): MutableList<StoreDetails>
//    fun findByAddressPeriodDateValidFromLessThanAndAddressPeriodDateValidUntilNotNull(refDate: LocalDate?): MutableList<StoreDetails>
//    fun findByAddressPeriodDateValidFromLessThanAndAddressPeriodDateValidUntilNull(refDate: LocalDate?): MutableList<StoreDetails>
}