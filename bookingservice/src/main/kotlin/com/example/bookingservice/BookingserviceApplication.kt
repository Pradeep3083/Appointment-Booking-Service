package com.example.bookingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class BookingServiceApplication

fun main(args: Array<String>) {
	runApplication<BookingServiceApplication>(*args)
}
