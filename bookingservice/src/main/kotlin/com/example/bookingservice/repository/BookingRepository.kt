package com.example.bookingservice.repository

import com.example.bookingservice.model.Booking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface BookingRepository : JpaRepository<Booking, Long> {

    @Query(
        "SELECT b FROM Booking b WHERE " +
                "b.professionalId = :professionalId AND " +
                "((b.startTime < :endTime AND b.endTime > :startTime))"
    )
    fun findOverlappingBookings(
        professionalId: Long,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): List<Booking>

    fun findAllByClientId(clientId: Long): List<Booking>

    fun findAllByProfessionalId(professionalId: Long): List<Booking>

    fun findAllByProfessionalIdAndStartTimeBetween(
        professionalId: Long,
        start: LocalDateTime,
        end: LocalDateTime
    ): List<Booking>
}
