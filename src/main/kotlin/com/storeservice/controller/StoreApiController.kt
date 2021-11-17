package com.storeservice.controller

import com.storeservice.data.dao.StoreAddressJPA
import com.storeservice.data.dao.StoreDetailsJPA
import com.storeservice.data.model.StoreDetails
import com.storeservice.exception.StoreNotFoundException
import com.storeservice.exception.StoresDataNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class StoreApiController @Autowired constructor(val storeAddressJpa: StoreAddressJPA, val storeDetails: StoreDetailsJPA) {

    @GetMapping("/store-service/v1/stores")
    fun findAllStoreDetails() : List<StoreDetails>{
        var allStoresDetails = storeDetails.findAll()
        if(allStoresDetails.isEmpty())
            throw StoresDataNotFoundException("Database has no record of any Store!")
        return allStoresDetails
    }

    @GetMapping("/store-service/v1/stores/{storeId}")
    fun findStoreById(@PathVariable storeId : Long) : Optional<StoreDetails> {
        val getStoreDetails = storeDetails.findById(storeId)
        if(getStoreDetails.isEmpty())
            throw StoreNotFoundException("Store ID : $storeId not found!")
        return getStoreDetails
    }
}