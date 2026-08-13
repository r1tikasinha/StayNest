
package com.ritikasinha.staynest.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.ritikasinha.staynest.data.FirebaseRepository
import com.ritikasinha.staynest.model.Booking
import com.ritikasinha.staynest.ui.theme.Background

@Composable
fun ProfileScreen(
    activity: Activity,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {

    val auth =
        FirebaseAuth.getInstance()

    val repository =
        remember {
            FirebaseRepository()
        }

    var bookings by remember {
        mutableStateOf<List<Booking>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(true)
    }

    val user = auth.currentUser

    LaunchedEffect(user?.uid) {

        if (user == null) {

            loading = false

            return@LaunchedEffect
        }

        repository.getUserBookings(

            userId = user.uid,

            onSuccess = { result ->

                bookings = result

                loading = false
            },

            onError = { error ->

                loading = false

                Toast.makeText(
                    activity,
                    error.message
                        ?: "Could not load bookings",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        // TOP BAR

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            TextButton(
                onClick = onBack
            ) {

                Text("← Back")
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "My Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.width(70.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            item {

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                // PROFILE HEADER

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)
                            .background(
                                MaterialTheme
                                    .colorScheme
                                    .primaryContainer
                            ),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Person,
                            contentDescription = null,
                            modifier =
                                Modifier.size(35.dp)
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.width(15.dp)
                    )

                    Column {

                        Text(
                            text = "Welcome!",
                            fontSize = 14.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )

                        Text(
                            text =
                                user?.email
                                    ?: "User",
                            fontSize = 17.sp,
                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(30.dp)
                )

                Text(
                    text = "Latest Booking",
                    fontSize = 22.sp,
                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )
            }

            // LOADING

            if (loading) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator()
                    }
                }
            }

            // NO BOOKINGS

            else if (bookings.isEmpty()) {

                item {

                    Card(
                        modifier =
                            Modifier.fillMaxWidth(),
                        shape =
                            RoundedCornerShape(18.dp)
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Text(
                                text =
                                    "No bookings yet",
                                fontSize = 18.sp,
                                fontWeight =
                                    FontWeight.Bold
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(6.dp)
                            )

                            Text(
                                text =
                                    "Book a room to see your reservation here."
                            )
                        }
                    }
                }
            }

            // LATEST BOOKING

            else {

                item {

                    val booking =
                        bookings.first()

                    BookingCard(
                        booking = booking
                    )

                    Spacer(
                        modifier =
                            Modifier.height(28.dp)
                    )

                    Text(
                        text = "Booking History",
                        fontSize = 22.sp,
                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )
                }

                items(bookings) { booking ->

                    BookingHistoryCard(
                        booking = booking
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )
                }
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                // LOGOUT

                OutlinedButton(
                    onClick = {

                        auth.signOut()

                        onLogout()
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),

                    shape =
                        RoundedCornerShape(14.dp)
                ) {

                    Text(
                        text = "↪",
                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Text("Logout")
                }

                Spacer(
                    modifier =
                        Modifier.height(30.dp)
                )
            }
        }
    }
}

@Composable
private fun BookingCard(
    booking: Booking
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation =
            CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Home,
                    contentDescription = null,
                    modifier =
                        Modifier.size(38.dp)
                )

                Spacer(
                    modifier =
                        Modifier.width(12.dp)
                )

                Column {

                    Text(
                        text = booking.roomName,
                        fontSize = 19.sp,
                        fontWeight =
                            FontWeight.Bold
                    )

                    Text(
                        text = booking.date,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            HorizontalDivider()

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            BookingRow(
                title = "Rooms",
                value = "${booking.roomCount}"
            )

            BookingRow(
                title = "Guest",
                value = booking.guestName
            )

            BookingRow(
                title = "Price / Room",
                value = "₹${booking.price}"
            )

            BookingRow(
                title = "Total Amount",
                value = "₹${booking.totalPrice}"
            )

            BookingRow(
                title = "Status",
                value = booking.status
            )
        }
    }
}


@Composable
private fun BookingHistoryCard(
    booking: Booking
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = booking.roomName,
                fontSize = 18.sp,
                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    "${booking.roomCount} room(s) • ₹${booking.totalPrice}"
            )

            Text(
                text = booking.date,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text = booking.status,
                color =
                    MaterialTheme
                        .colorScheme
                        .primary,
                fontWeight =
                    FontWeight.Bold
            )
        }
    }
}


@Composable
private fun BookingRow(
    title: String,
    value: String
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp
                ),
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )

        Text(
            text = value,
            fontWeight =
                FontWeight.Bold
        )
    }
}