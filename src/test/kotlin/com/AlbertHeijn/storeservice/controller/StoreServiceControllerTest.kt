package com.AlbertHeijn.storeservice.controller

import com.AlbertHeijn.storeservice.model.AddressPeriod
import com.AlbertHeijn.storeservice.model.StoreAddress
import com.AlbertHeijn.storeservice.model.StoreDetails
import com.AlbertHeijn.storeservice.service.StoreService
import org.junit.jupiter.api.Test

import org.mockito.Mockito
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@WebMvcTest(value = [StoreServiceController::class])
internal class StoreServiceControllerTest {
    @Autowired
    private lateinit var mockBean: MockMvc

    @MockBean
    private lateinit var storeDetailsRepo: StoreService

    private val storeCreatedAt = LocalDateTime.now()
    private val storeUpdatedAt = LocalDateTime.now()
    private val storeAddress = StoreAddress("a", 1, "b", 1, "c", "d")
    private val addressPeriod = listOf<AddressPeriod>(AddressPeriod(LocalDate.of(2021, 1,1), LocalDate.of(2022, 1,1), storeAddress))

    @Test
    fun findAllStoresDetails() {
        val storeDetails = listOf<StoreDetails>(StoreDetails(1, "store 1", "Active", storeCreatedAt, storeUpdatedAt, addressPeriod))

        Mockito.`when`(storeDetailsRepo.getStoreDetails(null, false)).thenReturn(storeDetails as List<StoreDetails>?)

        val requestBuilder: RequestBuilder = MockMvcRequestBuilders.get("/store-service/v1/stores")

        val result: MvcResult = mockBean.perform(requestBuilder).andReturn()
        println(result)
        val expected = "[{'storeId':1, 'name': 'store 1', 'status': 'Active','createdAt':'$storeCreatedAt','updatedAt':'$storeUpdatedAt','addressPeriod':[{'dateValidFrom': '2021-01-01', 'dateValidUntil': '2022-01-01', 'storeAddress':{'street': 'a', 'houseNumber':1, 'houseNumberSuffix': 'b', 'postalCode':1, 'city': 'c', 'country': 'd'}}]}]"
        JSONAssert.assertEquals(expected, result.response.contentAsString, false)
    }

    @Test
    fun findStoreById() {
        val storeDetails = Optional.ofNullable(StoreDetails(1, "store 1", "Active", storeCreatedAt, storeUpdatedAt, addressPeriod))

        Mockito.`when`(storeDetailsRepo.getStoreById(1)).thenReturn(storeDetails)
        val requestBuilder: RequestBuilder = MockMvcRequestBuilders.get("/store-service/v1/stores/1")

        val result: MvcResult = mockBean.perform(requestBuilder).andReturn()
        val expected = "{'storeId':1, 'name': 'store 1', 'status': 'Active','createdAt':'$storeCreatedAt','updatedAt':'$storeUpdatedAt','addressPeriod':[{'dateValidFrom': '2021-01-01', 'dateValidUntil': '2022-01-01', 'storeAddress':{'street': 'a', 'houseNumber':1, 'houseNumberSuffix': 'b', 'postalCode':1, 'city': 'c', 'country': 'd'}}]}"
        JSONAssert.assertEquals(expected, result.response.contentAsString, false)
    }

    @Test
    fun addStore() {
    }

    @Test
    fun updateStore() {
    }

    @Test
    fun putStore() {
    }

    @Test
    fun getStoreService() {
    }
}