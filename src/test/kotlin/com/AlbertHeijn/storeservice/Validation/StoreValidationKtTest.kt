package com.AlbertHeijn.storeservice.Validation

import com.AlbertHeijn.storeservice.model.AddressPeriod
import com.AlbertHeijn.storeservice.model.StoreAddress
import com.AlbertHeijn.storeservice.validation.IsValidAddressPeriod
import com.AlbertHeijn.storeservice.validation.IsValidCreateUpdateDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate
import java.time.LocalDateTime

internal class StoreValidationKtTest {

    @Test
    fun isValidStore() {

    }

    @Test
    fun isValidAddressPeriod() {
        val storeAddress = StoreAddress("a", 1, "b", 1, "c", "d")
        val addressPeriod = listOf<AddressPeriod>(AddressPeriod(LocalDate.now(), LocalDate.of(2022, 1,1), storeAddress))
        IsValidAddressPeriod(addressPeriod).apply {
            assertEquals(true, this)
        }
    }

    @Test
    fun isValidAddressPeriodNullRef() {
        val storeAddress = StoreAddress("a", 1, "b", 1, "c", "d")
        val addressPeriod = listOf<AddressPeriod>(AddressPeriod(LocalDate.now(), null, storeAddress))
        IsValidAddressPeriod(addressPeriod).apply {
            assertEquals(true, this)
        }
    }

    @Test
    fun isValidAddressPeriodPastRec() {
        val storeAddress = StoreAddress("a", 1, "b", 1, "c", "d")
        val addressPeriod = listOf<AddressPeriod>(AddressPeriod(LocalDate.of(2020, 1,1), null, storeAddress))
        IsValidAddressPeriod(addressPeriod).apply {
            assertEquals(false, this)
        }
    }

    @Test
    fun isValidCreateUpdateDate() {
        IsValidCreateUpdateDate(LocalDateTime.now(), LocalDateTime.now()).apply {
            assertEquals(true, this)
        }
    }

    @Test
    fun isValidCreateUpdateDateRev() {
        var createtAt = LocalDateTime.now()
        var updatedAt = LocalDateTime.now();
        IsValidCreateUpdateDate(updatedAt, createtAt).apply {
            assertEquals(false, this)
        }
    }
}