package com.example.rentalps.data

data class Booking(
    val title: String,
    val pricePerHour: String,
    val bookingDate: String,
    val playTime: String,
    val status: String = "Booked"
)