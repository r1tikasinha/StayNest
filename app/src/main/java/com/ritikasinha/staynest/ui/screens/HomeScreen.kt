package com.ritikasinha.staynest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritikasinha.staynest.model.Room
import com.ritikasinha.staynest.ui.theme.Background
import com.ritikasinha.staynest.ui.theme.Gold
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ritikasinha.staynest.R

private val rooms = listOf(

    Room(
        id = "room_101",
        name = "Deluxe King Room",
        description = "Spacious room with a king-size bed and city view.",
        price = 3499,
        rating = 4.8,
        beds = "1 King Bed",
        image = "deluxe_room"
    ),

    Room(
        id = "room_102",
        name = "Premium Suite",
        description = "Luxury suite with living area and premium facilities.",
        price = 5499,
        rating = 4.9,
        beds = "1 King Bed",
        image = "premium_suite"
    ),

    Room(
        id = "room_103",
        name = "Family Room",
        description = "Comfortable room designed for families.",
        price = 4299,
        rating = 4.7,
        beds = "2 Queen Beds",
        image = "family_room"
    )
)

@Composable
fun HomeScreen(
    onRoomClick: (Room) -> Unit,
    onProfileClick: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(20.dp)
    ) {

        // --------------------------------
        // HEADER
        // --------------------------------

        item {

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Good afternoon 👋",
                        fontSize = 16.sp,
                        color = MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Find your perfect stay",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // PROFILE BUTTON
                IconButton(
                    onClick = onProfileClick
                ) {

                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // --------------------------------
            // OFFER CARD
            // --------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.primary
                ),

                shape = RoundedCornerShape(20.dp)
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "Weekend Special",
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Up to 25% OFF",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Book your stay today",
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // --------------------------------
            // ROOMS TITLE
            // --------------------------------

            Text(
                text = "Popular rooms",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        // --------------------------------
        // ROOM LIST
        // --------------------------------

        items(rooms) { room ->

            RoomCard(
                room = room,
                onClick = {
                    onRoomClick(room)
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun RoomCard(
    room: Room,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column {

            // --------------------------------
            // ROOM IMAGE AREA
            // --------------------------------

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp
                        )
                    )
            ) {

                val imageResource = when (room.image) {

                    "deluxe_room" -> R.drawable.deluxe_room

                    "premium_suite" -> R.drawable.premium_suite

                    "family_room" -> R.drawable.family_room

                    else -> R.drawable.deluxe_room
                }

                Image(
                    painter = painterResource(
                        id = imageResource
                    ),
                    contentDescription = room.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // --------------------------------
            // ROOM INFORMATION
            // --------------------------------

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = room.name,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = room.description,
                    fontSize = 14.sp,
                    color = MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    // RATING

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Gold,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )

                        Text(
                            text = room.rating.toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // PRICE

                    Text(
                        text = "₹${room.price}/night",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}