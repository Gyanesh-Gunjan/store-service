package com.albertheijn.stores.controller

import com.albertheijn.stores.config.response.HttpResponseMsg
import com.albertheijn.stores.config.uri.STORES_END_POINT
import com.albertheijn.stores.config.uri.STORE_BY_ID_END_POINT

import com.albertheijn.stores.dto.StoreDto
import com.albertheijn.stores.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class StoresController(val storeService: StoreService) {

    @GetMapping(STORES_END_POINT)
    fun findAllStores(
        @RequestParam(name = "refDate", required = false) refDate : String?,
        @RequestParam(name = "futureFlag", required = false)  futureFlag : Boolean = false
    ): Any  = storeService.getStores(refDate, futureFlag)



    @GetMapping(STORE_BY_ID_END_POINT)
    fun findStoreById(@PathVariable storeId : Long): Any =
        storeService.getStoreByID(storeId)

    @DeleteMapping(STORE_BY_ID_END_POINT)
    fun deleteStoreById(@PathVariable storeId : Long): Unit = storeService.deleteByID(storeId)

    @PostMapping(STORES_END_POINT)
    fun addStore(@RequestBody storeDto: StoreDto): ResponseEntity<HttpResponseMsg> =
        storeService.saveStore(storeDto)


    @PatchMapping(STORES_END_POINT)
    fun updateStore(@RequestBody storeDto: StoreDto): ResponseEntity<HttpResponseMsg> =
        storeService.updateStore(storeDto)


    @PutMapping(STORES_END_POINT)
    fun putStore(@RequestBody storeDto: StoreDto): ResponseEntity<HttpResponseMsg> =
        storeService.saveStore(storeDto)
}