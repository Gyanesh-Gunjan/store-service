package com.AlbertHeijn.storeservice.validation

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun convertDate(refDate : String?): LocalDate {
    if(refDate == null) return LocalDate.now()
    try {
        val format = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ENGLISH)
        val refDateConverted = (LocalDate.parse(refDate.toString(), format))
        return refDateConverted
    }
    catch(e : Exception) {
        throw IllegalArgumentException("Reference Date (i.e. refDate) should be in YYYY-MM-DD format.")
    }
}
