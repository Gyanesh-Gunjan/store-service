package com.storeservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class StoresDataNotFoundException(message: String) : Exception(message) {
}
