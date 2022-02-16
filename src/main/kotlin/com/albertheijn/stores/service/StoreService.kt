package com.albertheijn.stores.service

import com.albertheijn.stores.config.response.HttpResponseMsg
import com.albertheijn.stores.dto.StoreDto
import com.albertheijn.stores.mapper.StoreMapper
import com.albertheijn.stores.repository.StoresRepo

import com.albertheijn.stores.validation.date.convertDate
import com.albertheijn.stores.validation.store.isValidStore
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component

class StoreService (private val storesRepo : StoresRepo){

    /*
    *   return list of stores based on the given conditions (i.e. refDate and futureFlag)
    *   Default values of refDate = currentDate and futureFlag = false
    *   if refDate format will Invalid then return HttpStatus.NOT_ACCEPTABLE response
    *   if futureFlag is false : return all the current records
    *   else return all the future records
    */

    fun getStores(refDate : String?, futureFlag : Boolean): Any {
        val dateRef = convertDate(refDate)
        println("From DB")
        return when {
            dateRef is ResponseEntity<*> -> dateRef
            futureFlag -> futureRecords(dateRef as LocalDate).map { StoreMapper().toDto(it) }
            else -> currentRecords(dateRef as LocalDate).map{ StoreMapper().toDto(it)}
        }
    }

    /*
    *   return store if  given storeId will present in the DB
    *   else return response HttpStatus.NOT_FOUND
    */

    fun getStoreByID(storeId : Long): Any  {
        val store = storesRepo.findById(storeId)
            .map { StoreMapper().toDto(it) }
        return if(store.isPresent) store
        else ResponseEntity(
            HttpResponseMsg("Not available in DB!"), HttpStatus.NOT_FOUND)
    }

    /*  Delete the store from DB */

    fun deleteByID(storeId : Long)  =
        storesRepo.deleteById(storeId)

    /*
    *   If given store will valid store then will save in the DB and return response HttpStatus.CREATED
    *   else return response HttpStatus.NOT_ACCEPTABLE
    */

    fun saveStore(storeDto: StoreDto) : ResponseEntity<HttpResponseMsg> {

        val storeEntity = StoreMapper().toEntity(storeDto, true)
        val isValidStoreDetails = isValidStore(storeEntity)

        return when{

            isValidStoreDetails  ->{
                storesRepo.save(storeEntity)
                ResponseEntity(HttpResponseMsg("StoreDetails saved!"), HttpStatus.CREATED)
            }

            else   -> ResponseEntity(
                HttpResponseMsg("Invalid store details!"), HttpStatus.NOT_ACCEPTABLE)

        }
    }

    /*
    *   If store is valid and storeId is present in the DB then it will update in the DB and
    *   return response HttpStatus.ACCEPTED
    *   If store is valid and storeId is not present in the DB then will return response HttpStatus.FORBIDDEN
    *   if store is Invalid then will return response HttpStatus.NOT_ACCEPTABLE
    */
    fun updateStore(storeDto: StoreDto) : ResponseEntity<HttpResponseMsg> {

        val isStorePresentInDB = storesRepo.findById(storeDto.storeId).isPresent
        val storeEntity = StoreMapper().toEntity(storeDto, true)
        val isValidStoreDetails = isValidStore(storeEntity)

        return when{

            isValidStoreDetails && isStorePresentInDB ->{
                storesRepo.save(storeEntity)
                ResponseEntity(HttpResponseMsg("StoreDetails updated!"), HttpStatus.ACCEPTED)
            }

            !isValidStoreDetails   -> ResponseEntity(
                HttpResponseMsg("Invalid store details!"),
                HttpStatus.NOT_ACCEPTABLE)

            else    ->   ResponseEntity(
                HttpResponseMsg( "store details not present in DB!"), HttpStatus.FORBIDDEN)
        }
    }

    /*
    *   return all future records based on the given refDate
    *   Def of futureRecord : dataValidFrom > refDate
    */
    private fun futureRecords(refDate: LocalDate)   =
        storesRepo.findAll().filter {
            it.addressPeriod =
                it.addressPeriod.filter { it -> it.dateValidFrom > refDate }
            it.addressPeriod.isNotEmpty()
        }


    /*
    *   return all current records based on the given refDate
    *   Def of currentRecords : dataValidFrom <= refDate <= dataValidUntil
    */
    private fun currentRecords(refDate: LocalDate) =
        storesRepo.findAll().filter {
            it.addressPeriod = it.addressPeriod
                .filter { it ->
                    it.dateValidFrom <= refDate && (it.dateValidUntil == null || it.dateValidUntil >= refDate)
                    }

            println(it.addressPeriod)
            it.addressPeriod.isNotEmpty()
        }
}
