package com.example.bookingservice.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "bookings",
    uniqueConstraints = [UniqueConstraint(columnNames = ["professional_id", "start_time", "end_time"])]
)
data class Booking(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "client_id")
    val clientId: Long,

    @Column(name = "professional_id")
    val professionalId: Long,

    @Column(name = "start_time")
    val startTime: LocalDateTime,

    @Column(name = "end_time")
    val endTime: LocalDateTime
)
