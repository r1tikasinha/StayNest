package com.ritikasinha.staynest.model

data class Booking(

    val bookingId: String = "",

    val userId: String = "",

    val roomId: String = "",

    val roomName: String = "",

    val price: Int = 0,

    val roomCount: Int = 1,

    val totalPrice: Int = 0,

    val guestName: String = "",

    val phone: String = "",

    val date: String = "",

    val status: String = "Confirmed"
)