package com.ritikasinha.staynest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritikasinha.staynest.ui.theme.Background
import com.ritikasinha.staynest.ui.theme.Success

@Composable
fun ConfirmationScreen(
    onGoHome: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Icon(
            imageVector =
                Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Success,
            modifier = Modifier.size(90.dp)
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Booking Confirmed!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text =
                "Your room has been successfully booked.",
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = onGoHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {

            Text("Back to Home")
        }
    }
}