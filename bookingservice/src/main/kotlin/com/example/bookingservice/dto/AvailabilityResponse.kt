package com.example.bookingservice.dto

import java.time.LocalDateTime

data class AvailabilityResponse(
    val professionalId: Long,
    val date: String,
    val availableSlots: List<Pair<LocalDateTime, LocalDateTime>>
)
