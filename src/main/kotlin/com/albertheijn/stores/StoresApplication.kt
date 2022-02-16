package com.albertheijn.stores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
class StoresApplication

fun main(args: Array<String>) {
	runApplication<StoresApplication>(*args)
}
