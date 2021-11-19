package com.storeservice.data.repository

import com.storeservice.data.model.StoreDetails
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreDetailsMongoDB : MongoRepository<StoreDetails, Long> {
}