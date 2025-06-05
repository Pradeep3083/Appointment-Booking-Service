package com.example.bookingservice.dto

import java.time.LocalDateTime

data class BookingRequest(
    val clientId: Long,
    val professionalId: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
