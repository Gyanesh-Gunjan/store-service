package com.AlbertHeijn.storeservice.dao

import com.AlbertHeijn.storeservice.model.StoreDetails
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreDetailsRepo : MongoRepository<StoreDetails, Long> {

}