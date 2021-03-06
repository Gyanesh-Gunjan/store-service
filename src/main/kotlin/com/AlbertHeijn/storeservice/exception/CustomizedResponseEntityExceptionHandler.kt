package com.AlbertHeijn.storeservice.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.webjars.NotFoundException
import java.lang.IllegalArgumentException
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        val exceptionResponse = ExceptionResponse(Date(), ex.message, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleArgumentMisMatchedException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<Any>? {
        val exceptionResponse = ExceptionResponse(Date(),  ex.message, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val exceptionResponse = ExceptionResponse(Date(),  ex.message, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND)
    }
}