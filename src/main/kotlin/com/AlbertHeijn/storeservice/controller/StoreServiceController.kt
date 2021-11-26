package com.AlbertHeijn.storeservice.controller

import com.AlbertHeijn.storeservice.configuration.STORES_DETAILS_END_POINT
import com.AlbertHeijn.storeservice.configuration.STORE_BY_ID_END_POINT
import com.AlbertHeijn.storeservice.configuration.STORE_DETAILS_ADD_END_POINT
import com.AlbertHeijn.storeservice.model.StoreDetails
import com.AlbertHeijn.storeservice.service.StoreService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController()
class StoreServiceController(val storeService: StoreService) {

    @GetMapping("$STORES_DETAILS_END_POINT")
    fun findAllStoresDetails(
        @RequestParam(name = "refDate", required = false) refDate: String?,
        @RequestParam(name="futureFlag", required = false) futureFlag: String? = "false"
    ): MutableList<StoreDetails> {

        println(refDate)
        println(futureFlag)
        return storeService.getStoreDetails(refDate, futureFlag)
    }

    @GetMapping("$STORE_BY_ID_END_POINT")
    fun findStoreById(@PathVariable storeId : String): Optional<StoreDetails> {
        return storeService.getStoreById(storeId)
    }


    @PostMapping("$STORE_DETAILS_ADD_END_POINT")
    fun addStore(@RequestBody store : StoreDetails){
         storeService.save(store)
    }
}