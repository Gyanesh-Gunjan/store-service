package com.AlbertHeijn.storeservice.service

import com.AlbertHeijn.storeservice.dao.StoreDetailsRepo
import com.AlbertHeijn.storeservice.model.StoreDetails
import com.AlbertHeijn.storeservice.validation.convertDate
import org.springframework.stereotype.Component
import org.webjars.NotFoundException
import java.time.LocalDate
import java.util.*

@Component
class StoreService (val storeDetailsRepo: StoreDetailsRepo) {

/*
*   return All stores records based on the given conditions (i.e. refDate and futureFlag)
*   Default value of refDate is current Date and futureFlag = 0
*   if futureFlag = 1 then it returns all future store records
*   if futureFlag = 0 then it returns all current store records
*   if futureFlag = -1 then it returns all past store records
*
*/
    fun getStoreDetails(refDate: String?, futureFlag: Int): List<StoreDetails> {
        val allStoresDetails = storeDetailsRepo
                                .findAll()
                                .ifEmpty { throw NotFoundException("Database has no records of any stores") }


        val dateRef = convertDate(refDate)

       return when (futureFlag) {
            1 -> futureRecords(allStoresDetails, dateRef)
            0 -> currentRecords(allStoresDetails, dateRef)
            else -> pastRecords(allStoresDetails, dateRef)
        }

    }


/*
*   return store details for the given storeId,
*   If store with given storeId is not present then it will throw NotFoundException
*
*/
    fun getStoreById(storeId: Long): Optional<StoreDetails> {

        return storeDetailsRepo
            .findById(storeId)
            .isEmpty
            .run { throw NotFoundException("Database has no record of store with Id $storeId") }
    }


    fun save(storeDetails: StoreDetails){
        storeDetailsRepo.save(storeDetails)
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


/*
*   return all current store records for which refDate less than
*   dateValidFrom and dateValidUntil of the AddressPeriod
*   if no record found then It will throw NotFoundException
*/
    private fun pastRecords(
        allStoresDetails : MutableList<StoreDetails>,
        dateRef: LocalDate) : List<StoreDetails> {

        allStoresDetails.forEach{ it1 ->
            it1.addressPeriod =
                it1.addressPeriod.filter{ it2 ->
                    it2.dateValidFrom < dateRef
                }
        }

        return allStoresDetails
            .filter { it1 -> it1.addressPeriod.isNotEmpty() }
            .ifEmpty{ throw NotFoundException("Database has no past Record with [ refDate - $dateRef ]") }

    }

}

