package com.ritikasinha.staynest.data

import com.google.firebase.database.*
import com.ritikasinha.staynest.model.Booking

class FirebaseRepository {

    private val database =
        FirebaseDatabase.getInstance().reference

    // SAVE BOOKING
    fun saveBooking(
        booking: Booking,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        val bookingId =
            database
                .child("bookings")
                .push()
                .key

        if (bookingId == null) {

            onError(
                Exception("Could not create booking ID")
            )

            return
        }

        val finalBooking =
            booking.copy(
                bookingId = bookingId
            )

        database
            .child("bookings")
            .child(bookingId)
            .setValue(finalBooking)
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener {

                onError(it)
            }
    }


    // GET ALL BOOKINGS OF USER
    fun getUserBookings(
        userId: String,
        onSuccess: (List<Booking>) -> Unit,
        onError: (Exception) -> Unit
    ) {

        database
            .child("bookings")
            .orderByChild("userId")
            .equalTo(userId)
            .addListenerForSingleValueEvent(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val bookings =
                            mutableListOf<Booking>()

                        for (child in snapshot.children) {

                            val booking =
                                child.getValue(
                                    Booking::class.java
                                )

                            if (booking != null) {

                                bookings.add(booking)
                            }
                        }

                        // Latest booking first
                        bookings.reverse()

                        onSuccess(bookings)
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                        onError(
                            error.toException()
                        )
                    }
                }
            )
    }
}