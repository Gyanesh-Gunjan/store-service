package com.albertheijn.stores.service


import com.albertheijn.stores.config.response.HttpResponseMsg
import com.albertheijn.stores.mapper.StoreMapper
import com.albertheijn.stores.repository.StoresRepo
import com.albertheijn.stores.samples.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import java.util.*


@SpringBootTest
internal class StoreServiceTest @Autowired constructor(
    @MockBean private val storesRepo: StoresRepo,
    private val storeService: StoreService
){

    @Test
    @DisplayName("getStore -> default Args")
    fun getStores_Default_Test() {
        Mockito.`when`(storesRepo.findAll()).thenReturn(storeDB1)
        assertEquals(
            storesListExp,
            storeService.getStores(LocalDate.now().toString(), false)
        )
    }

    @Test
    @DisplayName("getStore -> Invalid RefDate")
    fun getStores_InvalidDate_Test() {
        Mockito.`when`(storesRepo.findAll()).thenReturn(storesList)
        assertEquals(
            HttpStatus.NOT_ACCEPTABLE,
            (storeService.getStores("2019/002/03", false) as ResponseEntity<*>).statusCode
        )
    }

    @Test
    @DisplayName("getStore -> FutureRecords")
    fun getStores_FutureRec_Test() {
        Mockito.`when`(storesRepo.findAll()).thenReturn(storeDB)
        val result = storeService.getStores(LocalDate.now().toString(), true)
        assertEquals(expResult_FF, result)
    }

    @Test
    @DisplayName("getStoreById -> Available")
    fun getStoreByID_Test() {
        Mockito.`when`(storesRepo.findById(1001)).thenReturn(Optional.of(sampleStore1))
        assertEquals(
            Optional.of(StoreMapper().toDto(sampleStore1)),
            storeService.getStoreByID(1001)

        )
    }
    @Test
    @DisplayName("getStoreById -> NotAvailable")
    fun getStoreByID_NotAvailable_Test() {
        Mockito.`when`(storesRepo.findById(1003)).thenReturn(Optional.empty())

        assertEquals(
            HttpStatus.NOT_FOUND,
            (storeService.getStoreByID(1001) as ResponseEntity<HttpResponseMsg>).statusCode
        )
    }

    @Test
    @DisplayName("deleteById")
    fun deleteByID_Test() {
        Mockito.doNothing().`when`(storesRepo).deleteById(1001)
        assertEquals(Unit, storeService.deleteByID(1001))
    }

    @Test
    @DisplayName("saveStore -> ValidStore")
    fun saveStore_Valid_Test() {
        Mockito.`when`(storesRepo.save(StoreMapper().toEntity(storeSample)))
            .thenReturn(StoreMapper().toEntity(storeSample))

        assertEquals(
            HttpStatus.CREATED ,
            storeService.saveStore(storeSample).statusCode
        )
    }
    @Test
    @DisplayName("saveStore -> InValidStore")
    fun saveStore_Invalid_Test() {
        assertEquals(
            HttpStatus.NOT_ACCEPTABLE ,
            storeService.saveStore(InvalidStore).statusCode
        )
    }

    @Test
    @DisplayName("updateStore -> NotAvailable")
    fun updateStore_NotAvailable_Test() {
        Mockito.`when`(storesRepo.findById(1002)).thenReturn(Optional.empty())
        assertEquals(HttpStatus.FORBIDDEN, storeService.updateStore(notFoundStore).statusCode)
    }

    @Test
    @DisplayName("updateStore -> Available InvalidDetails")
    fun updateStore_Available_Invalid_Test() {
        Mockito.`when`(storesRepo.findById(1001)).thenReturn(Optional.of(sampleStore1))
//        Assertions.assertThrows(IllegalArgumentException::class.java){
//            storeService.updateStore(InvalidStore)
//        }
        assertEquals(HttpStatus.NOT_ACCEPTABLE, storeService.updateStore(InvalidStore).statusCode)
    }

    @Test
    @DisplayName("updateStore -> Available validDetails")
    fun updateStore_Available_Valid_test() {
        Mockito.`when`(storesRepo.findById(1001)).thenReturn(Optional.of(sampleStore1))

        assertEquals(
            HttpStatus.ACCEPTED,
            storeService.updateStore(StoreMapper().toDto(sampleStore1)).statusCode
        )
    }

}

