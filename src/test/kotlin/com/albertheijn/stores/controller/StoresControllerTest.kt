package com.albertheijn.stores.controller


import com.albertheijn.stores.config.response.HttpResponseMsg
import com.albertheijn.stores.config.uri.STORES_END_POINT

import com.albertheijn.stores.samples.*
import com.albertheijn.stores.service.StoreService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.*
import java.time.LocalDate
import java.util.*

@WebMvcTest(value = [StoresController::class])
internal class StoresControllerTest @Autowired constructor(
    private val objectMapper: ObjectMapper,
    private val mockBean: MockMvc,
    @MockBean private val storeService: StoreService
) {

    @Test
    @DisplayName("GET: /store-service/v1/stores")
    fun findAllStores_Test() {
        Mockito.`when`(storeService.getStores(LocalDate.now().toString(), false)).thenReturn(listOf(storeSample))
        mockBean.get(STORES_END_POINT)
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].storeId") { value("1001") }
                jsonPath("$[0].name") { value("AH Van Baerlestraat") }
            }
    }

    @Test
    @DisplayName("GET: /store-service/v1/stores?refDate=234-132-34&&futureFlag=true")
    fun findAllStore_WithRef_Test(){

        Mockito.`when`(storeService.getStores("234-132-34", true))
            .thenReturn(
                ResponseEntity(
                    HttpResponseMsg("Reference Date (i.e. $234-132-34) should tbe in YYYY-MM-DD format."),
                    HttpStatus.NOT_ACCEPTABLE)
            )

        mockBean.get("$STORES_END_POINT?refDate=234-132-34&&futureFlag=true")
            .andExpect {
                status { isNotAcceptable() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.message"){value("Reference Date (i.e. $234-132-34) should tbe in YYYY-MM-DD format.")}
            }
    }

    @Test
    @DisplayName("GET: /store-service/v1/stores/{storeId}")
    fun findStoreById_Test() {
        Mockito.`when`(storeService.getStoreByID(1001)).thenReturn(storeSample)
        mockBean.get("$STORES_END_POINT/1001")
            .andExpect {
                status { isOk() }
                jsonPath("$.name"){value("AH Van Baerlestraat")}
            }
    }

    @Test
    @DisplayName("Delete: /store-service/v1/stores/{storeId}")
    fun deleteStoreById() {
        Mockito.doNothing().`when`(storeService).deleteByID(1001)
        mockBean.delete("$STORES_END_POINT/1001").andExpect {
            status { isOk() }
        }
    }

    @Test
    @DisplayName("POST: /store-service/v1/stores")
    fun addStore() {
        Mockito.`when`(storeService.saveStore(storeSample)).thenReturn(
            ResponseEntity(HttpResponseMsg("StoreDetails saved!"), HttpStatus.CREATED)
        )

        mockBean.post(STORES_END_POINT){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(storeSample)
        }
            .andExpect {
                status { isCreated() }
                jsonPath("$.message"){value("StoreDetails saved!")}
        }
    }

    @Test
    @DisplayName("PATCH: /store-service/v1/stores")
    fun updateStore() {
        Mockito.`when`(storeService.updateStore(storeSample)).thenReturn(
            ResponseEntity(HttpResponseMsg("StoreDetails updated!"), HttpStatus.ACCEPTED)
        )

        mockBean.patch(STORES_END_POINT){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(storeSample)
        }
            .andExpect {
                status { isAccepted() }
                jsonPath("$.message"){value("StoreDetails updated!")}
            }
    }

    @Test
    @DisplayName("PUT: /store-service/v1/stores")
    fun putStore() {
        Mockito.`when`(storeService.saveStore(storeSample)).thenReturn(
            ResponseEntity(HttpResponseMsg("StoreDetails saved!"), HttpStatus.CREATED)
        )

        mockBean.put(STORES_END_POINT){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(storeSample)
        }
            .andExpect {
                status { isCreated() }
                jsonPath("$.message"){value("StoreDetails saved!")}
            }
    }

}