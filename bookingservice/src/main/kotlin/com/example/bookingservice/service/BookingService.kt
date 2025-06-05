package com.example.bookingservice.service

import com.example.bookingservice.dto.*
import com.example.bookingservice.model.Booking
import com.example.bookingservice.repository.BookingRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class BookingService(private val repository: BookingRepository) {

    @Transactional
    fun createBooking(request: BookingRequest): Booking? {
        val overlaps = repository.findOverlappingBookings(
            request.professionalId, request.startTime, request.endTime
        )
        if (overlaps.isNotEmpty()) return null

        val booking = Booking(
            clientId = request.clientId,
            professionalId = request.professionalId,
            startTime = request.startTime,
            endTime = request.endTime
        )
        return repository.save(booking)
    }

    fun getBookings(
        clientId: Long?,
        professionalId: Long?,
        date: LocalDate?
    ): List<Booking> {
        return repository.findAll().filter {
            (clientId == null || it.clientId == clientId) &&
                    (professionalId == null || it.professionalId == professionalId) &&
                    (date == null || it.startTime.toLocalDate() == date)
        }
    }

    fun deleteBooking(id: Long): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else false
    }

    @Cacheable("availability")
    fun getAvailability(professionalId: Long, date: LocalDate): AvailabilityResponse {
        val startOfDay = date.atTime(LocalTime.of(9, 0))
        val endOfDay = date.atTime(LocalTime.of(17, 0))

        val bookings = repository.findAllByProfessionalIdAndStartTimeBetween(
            professionalId, startOfDay, endOfDay
        ).sortedBy { it.startTime }

        val slots = mutableListOf<Pair<LocalDateTime, LocalDateTime>>()

        var current = startOfDay
        for (booking in bookings) {
            if (current < booking.startTime) {
                slots.add(current to booking.startTime)
            }
            current = maxOf(current, booking.endTime)
        }
        if (current < endOfDay) {
            slots.add(current to endOfDay)
        }

        return AvailabilityResponse(professionalId, date.toString(), slots)
    }
}
