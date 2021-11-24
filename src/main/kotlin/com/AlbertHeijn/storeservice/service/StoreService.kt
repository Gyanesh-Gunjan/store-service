package com.AlbertHeijn.storeservice.service

import com.AlbertHeijn.storeservice.dao.StoreDetailsRepo
import com.AlbertHeijn.storeservice.model.StoreDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class StoreService (val storeDetailsRepo: StoreDetailsRepo) {

    fun getStoreDetails(): MutableList<StoreDetails> {
        return storeDetailsRepo.findAll()
    }

    fun getStoreById(storeId : Long): Optional<StoreDetails> {
        return storeDetailsRepo.findById(storeId)
    }

    fun save(storeDetails: StoreDetails){
        storeDetailsRepo.save(storeDetails)
    }
}