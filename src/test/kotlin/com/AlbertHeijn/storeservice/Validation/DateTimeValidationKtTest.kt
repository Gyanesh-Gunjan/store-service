package com.AlbertHeijn.storeservice.Validation

import com.AlbertHeijn.storeservice.validation.convertDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

internal class DateTimeValidationKtTest {

    @Test
    fun convertDateTest(){
        var result = convertDate("2020-01-01")
        var expected = LocalDate.of(2020, 1,1)
        assertEquals(expected, result)
    }

    @Test
    fun convertDateNullRefArgTest(){
        var result = convertDate(null)
        var expected = LocalDate.now()
        assertEquals(expected, result)
    }
}