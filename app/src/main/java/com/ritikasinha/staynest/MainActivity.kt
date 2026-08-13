package com.ritikasinha.staynest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.ritikasinha.staynest.model.Room
import com.ritikasinha.staynest.navigation.Screen
import com.ritikasinha.staynest.ui.screens.CheckoutScreen
import com.ritikasinha.staynest.ui.screens.ConfirmationScreen
import com.ritikasinha.staynest.ui.screens.HomeScreen
import com.ritikasinha.staynest.ui.screens.LoginScreen
import com.ritikasinha.staynest.ui.screens.RoomDetailsScreen
import com.ritikasinha.staynest.ui.theme.StayNestTheme
import com.ritikasinha.staynest.ui.screens.ProfileScreen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.IconButton

class MainActivity : ComponentActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            StayNestTheme {

                val navController = rememberNavController()

                var selectedRoom by remember {
                    mutableStateOf<Room?>(null)
                }

                NavHost(
                    navController = navController,

                    startDestination =
                        if (auth.currentUser != null) {
                            Screen.Home.route
                        } else {
                            Screen.Login.route
                        }
                ) {

                    // LOGIN

                    composable(Screen.Login.route) {

                        LoginScreen(
                            activity = this@MainActivity,

                            onLoginSuccess = {

                                navController.navigate(
                                    Screen.Home.route
                                ) {
                                    popUpTo(
                                        Screen.Login.route
                                    ) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    // HOME

                    composable(Screen.Home.route) {

                        HomeScreen(

                            onRoomClick = { room ->

                                selectedRoom = room

                                navController.navigate(
                                    Screen.RoomDetails.route
                                )
                            },

                            onProfileClick = {

                                navController.navigate(
                                    Screen.Profile.route
                                )
                            }
                        )
                    }

                    // ROOM DETAILS

                    composable(Screen.RoomDetails.route) {

                        selectedRoom?.let { room ->

                            RoomDetailsScreen(
                                room = room,

                                onBookNow = {

                                    navController.navigate(
                                        Screen.Checkout.route
                                    )
                                },

                                onBack = {

                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    // CHECKOUT

                    composable(Screen.Checkout.route) {

                        selectedRoom?.let { room ->

                            CheckoutScreen(
                                room = room,

                                onBookingComplete = {

                                    navController.navigate(
                                        Screen.Confirmation.route
                                    ) {

                                        popUpTo(
                                            Screen.Home.route
                                        )
                                    }
                                },

                                onBack = {

                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    // CONFIRMATION

                    composable(
                        Screen.Confirmation.route
                    ) {

                        ConfirmationScreen(
                            onGoHome = {

                                navController.navigate(
                                    Screen.Home.route
                                ) {

                                    popUpTo(
                                        Screen.Home.route
                                    ) {
                                        inclusive = true
                                    }
                                }
                            }
                        )

                    }

                    // PROFILE
                    composable(Screen.Profile.route) {

                        ProfileScreen(
                            activity = this@MainActivity,

                            onBack = {
                                navController.popBackStack()
                            },

                            onLogout = {

                                navController.navigate(
                                    Screen.Login.route
                                ) {
                                    popUpTo(
                                        Screen.Home.route
                                    ) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }

                }
            }
        }
    }
}