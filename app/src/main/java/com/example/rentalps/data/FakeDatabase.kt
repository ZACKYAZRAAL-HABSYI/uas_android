package com.example.rentalps.data

object FakeDatabase {
    val users = mutableListOf<User>()
    val bookingList  = mutableListOf<Booking>()

    fun register(user: User): Boolean {
        if (users.any { it.email == user.email }) return false
        users.add(user)
        return true
    }

    fun login(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }

    fun addBooking(booking: Booking) {
        bookingList .add(booking)
    }

    fun getBookings(): List<Booking> = bookingList
}