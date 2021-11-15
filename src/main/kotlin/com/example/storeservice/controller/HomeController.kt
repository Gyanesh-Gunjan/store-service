package com.example.storeservice.controller

import com.example.storeservice.data.entity.AddressPeriod
import com.example.storeservice.data.entity.StoreAddress
import com.example.storeservice.data.entity.Stores
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

@RestController
class HomeController {

    @GetMapping("/store-service/v1/stores")
    fun getAll() : List<Stores>{
        val storeaddress = StoreAddress(
            "london street",
            "123",
            "4M",
            "23453",
            "london",
            "UK")
        val addressPeriod = listOf(AddressPeriod(
            SimpleDateFormat("yyyy-MM-dd").format(Date()),
            SimpleDateFormat("yyyy-MM-dd").format(Date()),
            listOf(storeaddress)
        ))
        val stores = listOf(
            Stores(
                1001,
                "ahold",
                "active",
                Date(),
                Date(),
                addressPeriod
            )
        )

        return stores
    }

    @GetMapping("/store-service/v1/stores/{storeId}")
    fun getById(@PathVariable storeId : Int) : String{
        return "this is for getting details by {$storeId}"
    }

}