package com.AlbertHeijn.storeservice.service

import com.AlbertHeijn.storeservice.dao.StoreDetailsRepo
import com.AlbertHeijn.storeservice.model.StoreDetails
import com.AlbertHeijn.storeservice.validation.IsValidStore
import com.AlbertHeijn.storeservice.validation.convertDate
import org.springframework.stereotype.Component
import org.webjars.NotFoundException
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*

@Component
class StoreService (val storeDetailsRepo: StoreDetailsRepo) {

/*
*   return All stores records based on the given conditions (i.e. refDate and futureFlag)
*   Default value of refDate is current Date and futureFlag = false
*   if futureFlag = true then it returns all future store records
*   if futureFlag = false then it returns all current store records
*
*/
    fun getStoreDetails(refDate: String?, futureFlag: Boolean): List<StoreDetails> {
        val allStoresDetails = storeDetailsRepo
                                .findAll()
                                .ifEmpty { throw NotFoundException("Database has no records of any stores") }


        val dateRef = convertDate(refDate)

        return if(futureFlag) futureRecords(allStoresDetails, dateRef)
        else currentRecords( allStoresDetails, dateRef)
    }


/*
*   return store details for the given storeId,
*   If store with given storeId is not present then it will throw NotFoundException
*
*/
    fun getStoreById(storeId: Long): Optional<StoreDetails> =
         storeDetailsRepo
            .findById(storeId)
            .isEmpty
            .run { throw NotFoundException("Database has no record of store with Id $storeId") }


/*
*   saving store details to the database
*/
    fun saveStore(storeDetails: StoreDetails) {
        try {
            when {
                IsValidStore(storeDetails) &&
                        storeDetailsRepo.findByName(storeDetails.name).isEmpty -> storeDetailsRepo.save(storeDetails)
                else -> throw IllegalArgumentException("Invalid dates or store Name is already present in the database")
            }
        } catch (e: Exception) { throw IllegalArgumentException("Invalid data types") }
    }


/*
*   delete store by given storeId
*   if storeId is not present in the database then it will throw NotFoundException
*   else delete store details by given storeId
*/
    fun deleteById(storeId : Long) = if(storeDetailsRepo
                .findById(storeId)
                .isEmpty)
            throw NotFoundException("Database has no record of store with Id $storeId")
        else storeDetailsRepo.deleteById(storeId)



/*
*   storeUpdate is for updating store details in the database based on the conditions
*   default value of putFlag is false
*
*   if putFlag is true then
*       .  if given storeId will present in the database then it will simply update that particular store details
*          else will that store details as n new records
*
*   if putFlag is false then
*       . if given storeId will present in the database then it will simply update that particular store details
*          else will throw NotFoundException
*/

    fun storeUpdate(storeDetails: StoreDetails, putFlag : Boolean) =
        when {
            putFlag && storeDetailsRepo.findById(storeDetails.storeId).isEmpty  -> saveStore(storeDetails)
            !putFlag && storeDetailsRepo.findById(storeDetails.storeId).isPresent -> {
                deleteById(storeDetails.storeId)
                saveStore(storeDetails)
            }
            else -> throw NotFoundException("Database has no record of store with Id ${storeDetails.storeId}")
        }



/*
*   return all current store records for which refDate lie between
*   dateValidFrom and dateValidUntil of the AddressPeriod
*   if no record found then It will throw NotFoundException
*/
    private fun currentRecords(
        allStoresDetails: MutableList<StoreDetails>,
        dateRef: LocalDate) : List<StoreDetails> {

        allStoresDetails.forEach{ it1 ->
            it1.addressPeriod =
                it1.addressPeriod.filter{ it2 ->
                    it2.dateValidFrom<= dateRef &&
                            (it2.dateValidUntil == null ||  it2.dateValidUntil!! >= dateRef)
                }
        }

        return allStoresDetails
            .filter { it1 -> it1.addressPeriod.isNotEmpty() }
            .ifEmpty { throw NotFoundException("Database has no current Record with [ refDate - $dateRef ]") }


    }

/*
*   return all current store records for which refDate is greater than
*   dateValidFrom and dateValidUntil of the AddressPeriod
*   if no record found then It will throw NotFoundException
*/
    private fun futureRecords(
        allStoresDetails : MutableList<StoreDetails>,
        dateRef: LocalDate) : List<StoreDetails> {

        allStoresDetails.forEach{ it1 ->
            it1.addressPeriod =
                it1.addressPeriod.filter{ it2 ->
                    it2.dateValidFrom > dateRef
                }
        }

        return allStoresDetails
            .filter { it1 -> it1.addressPeriod.isNotEmpty() }
            .ifEmpty{ throw NotFoundException("Database has no future Record with [ refDate - $dateRef ]") }
    }


}

