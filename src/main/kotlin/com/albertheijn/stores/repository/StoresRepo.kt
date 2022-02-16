package com.albertheijn.stores.repository

import com.albertheijn.stores.model.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoresRepo : MongoRepository<Store, Long>

