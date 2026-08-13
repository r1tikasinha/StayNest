package com.ritikasinha.staynest.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.ritikasinha.staynest.ui.theme.Background
import com.ritikasinha.staynest.ui.theme.Blue

@Composable
fun LoginScreen(
    activity: Activity,
    onLoginSuccess: () -> Unit
) {

    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var isSignUp by remember {
        mutableStateOf(false)
    }

    fun authenticate() {

        if (email.isBlank() || password.isBlank()) {

            Toast.makeText(
                activity,
                "Please enter email and password",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (password.length < 6) {

            Toast.makeText(
                activity,
                "Password must be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        loading = true

        if (isSignUp) {

            // CREATE ACCOUNT
            auth.createUserWithEmailAndPassword(
                email.trim(),
                password
            ).addOnCompleteListener { task ->

                loading = false

                if (task.isSuccessful) {

                    Toast.makeText(
                        activity,
                        "Account created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    onLoginSuccess()

                } else {

                    Toast.makeText(
                        activity,
                        task.exception?.message
                            ?: "Account creation failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        } else {

            // LOGIN
            auth.signInWithEmailAndPassword(
                email.trim(),
                password
            ).addOnCompleteListener { task ->

                loading = false

                if (task.isSuccessful) {

                    Toast.makeText(
                        activity,
                        "Login successful!",
                        Toast.LENGTH_SHORT
                    ).show()

                    onLoginSuccess()

                } else {

                    Toast.makeText(
                        activity,
                        task.exception?.message
                            ?: "Login failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            tint = Blue,
            modifier = Modifier.size(70.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "StayNest",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Blue
        )

        Text(
            text = "Comfort stays. Beautiful memories.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        Text(
            text = if (isSignUp)
                "Create your account"
            else
                "Welcome back!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // EMAIL
        OutlinedTextField(
            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            singleLine = true,

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // PASSWORD
        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            singleLine = true,

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // LOGIN / SIGN UP BUTTON
        Button(
            onClick = {
                authenticate()
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
                    text = if (isSignUp)
                        "Create Account"
                    else
                        "Login",

                    fontSize = 16.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        // SWITCH LOGIN / SIGNUP
        TextButton(
            onClick = {
                isSignUp = !isSignUp
            }
        ) {

            Text(
                text = if (isSignUp)
                    "Already have an account? Login"
                else
                    "Don't have an account? Sign Up"
            )
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "By continuing, you agree to our Terms & Privacy Policy",
            fontSize = 12.sp,
            color = MaterialTheme
                .colorScheme
                .onSurfaceVariant
        )
    }
}

