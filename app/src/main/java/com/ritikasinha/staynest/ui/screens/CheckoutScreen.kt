
package com.ritikasinha.staynest.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.ritikasinha.staynest.data.FirebaseRepository
import com.ritikasinha.staynest.model.Booking
import com.ritikasinha.staynest.model.Room
import com.ritikasinha.staynest.ui.theme.Background
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CheckoutScreen(
    room: Room,
    onBookingComplete: () -> Unit,
    onBack: () -> Unit
) {

    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var roomCount by remember {
        mutableStateOf(1)
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val repository = remember {
        FirebaseRepository()
    }

    val totalPrice = room.price * roomCount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(20.dp)
    ) {

        TextButton(
            onClick = onBack
        ) {
            Text("← Back")
        }

        Text(
            text = "Checkout",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        // ROOM DETAILS

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = room.name,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "₹${room.price} / room"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ROOM COUNT

        Text(
            text = "Number of Rooms",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedButton(
                onClick = {

                    if (roomCount > 1) {
                        roomCount--
                    }
                }
            ) {
                Text("−")
            }

            Text(
                text = "$roomCount",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(
                onClick = {

                    if (roomCount < 10) {
                        roomCount++
                    }
                }
            ) {
                Text("+")
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // GUEST NAME

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Guest Name")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // PHONE

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            label = {
                Text("Phone Number")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // PAYMENT

        Text(
            text = "Payment",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "💳  Pay at Hotel",
                modifier = Modifier.padding(18.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        // TOTAL

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Total Amount",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "₹$totalPrice",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        // CONFIRM BOOKING

        Button(
            onClick = {

                if (name.isBlank() || phone.isBlank()) {

                    Toast.makeText(
                        context,
                        "Please fill all details",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                loading = true

                val userId =
                    FirebaseAuth
                        .getInstance()
                        .currentUser
                        ?.uid

                if (userId == null) {

                    loading = false

                    Toast.makeText(
                        context,
                        "Please login again",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val date =
                    SimpleDateFormat(
                        "dd MMM yyyy HH:mm",
                        Locale.getDefault()
                    ).format(Date())

                val booking = Booking(

                    userId = userId,

                    roomId = room.id,

                    roomName = room.name,

                    price = room.price,

                    roomCount = roomCount,

                    totalPrice = totalPrice,

                    guestName = name,

                    phone = phone,

                    date = date,

                    status = "Confirmed"
                )

                repository.saveBooking(

                    booking = booking,

                    onSuccess = {

                        loading = false

                        Toast.makeText(
                            context,
                            "Booking confirmed!",
                            Toast.LENGTH_SHORT
                        ).show()

                        onBookingComplete()
                    },

                    onError = {

                        loading = false

                        Toast.makeText(
                            context,
                            "Booking failed: ${it.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },

            enabled = !loading,

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(14.dp)
        ) {

            if (loading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = MaterialTheme
                        .colorScheme
                        .onPrimary
                )

            } else {

                Text(
                    text = "Confirm Booking • ₹$totalPrice",
                    fontSize = 16.sp
                )
            }
        }
    }
}