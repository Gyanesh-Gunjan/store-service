package com.albertheijn.stores.validation.date

import com.albertheijn.stores.config.date.format
import com.albertheijn.stores.config.response.HttpResponseMsg
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDate


/*
*    convert refDate string into localDate
*    if refDate will null then return current Date
*    if refDate format will Invalid then return response HttpStatus.NOT_ACCEPTABLE
*/
fun convertDate(refDate : String?): Any =
    when {
        refDate.isNullOrEmpty() -> LocalDate.now()
        else -> try {
            LocalDate.parse(refDate.toString(), format)
        } catch(e : Exception) {
            ResponseEntity(
                HttpResponseMsg("Reference Date (i.e. $refDate) should tbe in YYYY-MM-DD format."),
                HttpStatus.NOT_ACCEPTABLE)
        }
    }

