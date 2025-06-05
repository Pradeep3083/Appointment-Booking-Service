package com.example.bookingservice.controller

import com.example.bookingservice.dto.BookingRequest
import com.example.bookingservice.service.BookingService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/bookings")
class BookingController(private val service: BookingService) {

    @PostMapping
    fun create(@RequestBody request: BookingRequest): ResponseEntity<Any> {
        val created = service.createBooking(request)
        return if (created != null) ResponseEntity.status(HttpStatus.CREATED).body(created)
        else ResponseEntity.status(HttpStatus.CONFLICT).body("Time slot already booked.")
    }

    @GetMapping
    fun list(
        @RequestParam(required = false) clientId: Long?,
        @RequestParam(required = false) professionalId: Long?,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(service.getBookings(clientId, professionalId, date))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (service.deleteBooking(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/availability")
    fun availability(
        @RequestParam professionalId: Long,
        @RequestParam
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(service.getAvailability(professionalId, date))
    }
}
