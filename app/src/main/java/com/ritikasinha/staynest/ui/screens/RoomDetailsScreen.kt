package com.ritikasinha.staynest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritikasinha.staynest.R
import com.ritikasinha.staynest.model.Room
import com.ritikasinha.staynest.ui.theme.Background
import com.ritikasinha.staynest.ui.theme.Gold

@Composable
fun RoomDetailsScreen(
    room: Room,
    onBookNow: () -> Unit,
    onBack: () -> Unit
) {

    val imageResource = when (room.image) {

        "deluxe_room" ->
            R.drawable.deluxe_room

        "premium_suite" ->
            R.drawable.premium_suite

        "family_room" ->
            R.drawable.family_room

        else ->
            R.drawable.deluxe_room
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        // BACK BUTTON

        TextButton(
            onClick = onBack,
            modifier = Modifier.padding(
                start = 8.dp,
                top = 8.dp
            )
        ) {
            Text(
                text = "← Back",
                fontSize = 16.sp
            )
        }

        // CONTENT

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
        ) {

            // ROOM IMAGE

            Image(
                painter = painterResource(
                    id = imageResource
                ),
                contentDescription = room.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ROOM NAME

            Text(
                text = room.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // RATING

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Gold,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                Text(
                    text = "${room.rating}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                Text(
                    text = "Excellent",
                    color = MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // DESCRIPTION

            Text(
                text = room.description,
                color = MaterialTheme
                    .colorScheme
                    .onSurfaceVariant,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // FEATURES

            Text(
                text = "Room Features",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            FeatureItem(
                icon = "🛏️",
                text = room.beds
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            FeatureItem(
                icon = "📶",
                text = "Free Wi-Fi"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            FeatureItem(
                icon = "❄️",
                text = "Air Conditioning"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            FeatureItem(
                icon = "🍳",
                text = "Breakfast included"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            FeatureItem(
                icon = "🛎️",
                text = "24/7 Room Service"
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // PRICE + BOOK BUTTON

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment =
                        Alignment.CenterVertically,
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Column {

                        Text(
                            text = "Price",
                            fontSize = 13.sp,
                            color = MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        )

                        Text(
                            text = "₹${room.price}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "per night",
                            fontSize = 12.sp,
                            color = MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        )
                    }

                    Button(
                        onClick = onBookNow,
                        modifier = Modifier
                            .height(52.dp)
                            .width(140.dp),
                        shape =
                            RoundedCornerShape(14.dp)
                    ) {

                        Text(
                            text = "Book Now",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureItem(
    icon: String,
    text: String
) {

    Row(
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = icon,
            fontSize = 20.sp
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Text(
            text = text,
            fontSize = 15.sp
        )
    }
}