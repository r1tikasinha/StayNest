package com.ritikasinha.staynest.navigation

sealed class Screen(val route: String) {

    data object Login : Screen("login")

    data object Otp : Screen("otp")

    data object Home : Screen("home")

    data object RoomDetails : Screen("room_details")

    data object Checkout : Screen("checkout")

    data object Confirmation : Screen("confirmation")

    data object Profile : Screen("profile")
}