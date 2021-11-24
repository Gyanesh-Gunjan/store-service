package com.AlbertHeijn.storeservice.controller

import com.AlbertHeijn.storeservice.model.StoreDetails
import com.AlbertHeijn.storeservice.service.StoreService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController()
@RequestMapping("/store-service/v1")
class StoreServiceController(val storeService: StoreService) {

    @GetMapping("/stores")
    fun findAllStoresDetails(): MutableList<StoreDetails> {
        return storeService.getStoreDetails()
    }

    @GetMapping("/stores/{storeId}")
    fun findStoreById(@PathVariable storeId : Long): Optional<StoreDetails> {
        return storeService.getStoreById(storeId)
    }

    @PostMapping("/stores/add")
    fun addStore(@RequestBody store : StoreDetails){
         storeService.save(store)
    }
}