package com.AlbertHeijn.storeservice.service

import com.AlbertHeijn.storeservice.dao.StoreDetailsRepo
import com.AlbertHeijn.storeservice.model.StoreDetails
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.apache.catalina.Store
import org.springframework.stereotype.Component
import org.webjars.NotFoundException
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class StoreService (val storeDetailsRepo: StoreDetailsRepo) {

    fun getStoreDetails(refDate: String?, futureFlag: String?): MutableList<StoreDetails> {
        val allStoresDetails : MutableList<StoreDetails>?

        if(refDate != null && futureFlag.equals("true",true)){
            val dateConverted = convertDate(refDate)
            allStoresDetails = storeDetailsRepo
                .findByAddressPeriodDateValidFromLessThanAndAddressPeriodDateValidUntilNull(dateConverted)
        }
        else if(refDate != null && (futureFlag.equals("false", true) || futureFlag == null)){
            val dateConverted = convertDate(refDate)
            allStoresDetails = storeDetailsRepo
                .findByAddressPeriodDateValidFromLessThan(dateConverted)
        }
        else allStoresDetails =  storeDetailsRepo.findAll()

        if(allStoresDetails.isEmpty())
            throw NotFoundException("Database has no records of any stores")
        return allStoresDetails
    }

    fun getStoreById(storeId : String): Optional<StoreDetails> {
        val ID : Long?
        try{
             ID = storeId.toLong()
        }
        catch(e : Exception){
            throw IllegalArgumentException("storeId type should be number.")
        }
        val storeDetails = storeDetailsRepo.findById(ID)
        if(storeDetails.isEmpty)
            throw NotFoundException("Database has no record of store with Id $ID")
        return storeDetails
    }


    fun save(storeDetails: StoreDetails){
        storeDetailsRepo.save(storeDetails)
    }

    private fun convertDate(date : String): LocalDate? {
        try {
            val format = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH)
            val refDateConverted = (LocalDate.parse(date.toString(), format))
            return refDateConverted
        }
        catch(e : Exception) {
            throw IllegalArgumentException("Reference Date (i.e. refDate) should be in YYYY-MM-DD format.")
        }
    }

}

