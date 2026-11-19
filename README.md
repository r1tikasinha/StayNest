# StayNest – Android Hotel Booking Application

##  Project Overview

**StayNest** is an Android hotel booking application developed using Kotlin and Jetpack Compose. It provides users with a simple and intuitive interface to explore available rooms, select their preferred accommodation, and complete the booking process.

The application integrates Firebase Authentication for user authentication and Firebase Realtime Database for storing booking and user-related information.

The project focuses on creating a smooth hotel booking experience with modern Android UI development, cloud database integration, and structured application workflows.

---

##  Features

###  User Authentication
- User registration and login.
- Firebase Authentication integration.
- Secure email and password authentication.
- Authentication-based access to the application.

###  Room Browsing & Selection
- Browse available hotel rooms.
- View room details and pricing.
- Select a room based on user preferences.
- Interactive room selection interface.

###  Booking Management
- Book selected hotel rooms.
- Enter guest details.
- Provide contact information.
- Select booking dates.
- Store booking details in Firebase Realtime Database.

###  Booking & Checkout
- Booking confirmation workflow.
- Checkout screen with booking details.
- Display selected room, guest information, and booking price.
- Manage booking information during checkout.

###  User Profile
- Display user-related information.
- Access booking details.
- View last booking information.
- Display booking-related statistics such as room count.

###  Firebase Integration
- Firebase Authentication for user login and registration.
- Firebase Realtime Database for cloud data storage.
- Store booking details and user-related information.
- Retrieve data for application features.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Android application development |
| Jetpack Compose | Modern declarative UI development |
| Firebase Authentication | User registration and login |
| Firebase Realtime Database | Cloud database and booking storage |
| Android Studio | Development environment |
| Material Design | UI components and styling |

---

##  Application Workflow

1. User opens the StayNest application.
2. User registers or logs in using their email and password.
3. User explores available hotel rooms.
4. User selects a preferred room.
5. User enters guest details and booking information.
6. User proceeds to the checkout screen.
7. Booking information is stored in Firebase Realtime Database.
8. User can access booking-related information through their profile.

---

##  Firebase Database Structure

### Booking

Each booking contains information such as:

| Field | Description |
|---|---|
| bookingId | Unique booking identifier |
| userId | ID of the user who made the booking |
| roomId | Selected room identifier |
| roomName | Name of the selected room |
| price | Booking price |
| guestName | Guest's name |
| phone | Guest's contact number |
| date | Booking date |
| status | Booking status |

### User Profile

User-related information can include:

- User ID
- Profile information
- Last booking details
- Room booking count

Firebase Realtime Database is used to store and retrieve the relevant booking and user data.

---

##  Application Architecture & Development

The application is developed using Kotlin and Jetpack Compose, following a modern Android development approach.

- Composable functions are used to build reusable UI elements.
- UI components are organized for different application screens.
- State is used to manage UI interactions and screen updates.
- Firebase Authentication handles user authentication.
- Firebase Realtime Database manages booking and user data.

---

##  Project Objectives

- Build a user-friendly Android hotel booking application.
- Develop modern Android interfaces using Jetpack Compose.
- Integrate Firebase Authentication.
- Store and retrieve booking information using Firebase.
- Implement room selection and booking workflows.
- Understand Android application development and cloud backend integration.

---

##  Key Learnings

- Android Development with Kotlin
- Jetpack Compose
- Composable UI Components
- UI State Management
- Firebase Authentication
- Firebase Realtime Database
- CRUD Operations
- User Authentication Workflow
- Database Integration
- Android Application Architecture

---

##  Future Improvements

- Online payment gateway integration.
- Real-time room availability.
- Booking cancellation and modification.
- Hotel search and filtering.
- Booking history.
- Improved profile management.
- Notifications for booking confirmation and updates.

---
<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 17 36 PM" src="https://github.com/user-attachments/assets/a275cb53-0b0e-42fc-9ddb-fd3dc293c7c5" />

<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 09 25 PM" src="https://github.com/user-attachments/assets/580f0ffc-42c1-4f28-bc8e-4046b28c2887" />

<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 09 31 PM" src="https://github.com/user-attachments/assets/76759f38-34d6-4793-96b5-90ede5b5ed0f" />

<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 09 39 PM" src="https://github.com/user-attachments/assets/d33039ee-e5fb-4c2b-acec-766ef9205948" />

<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 09 54 PM" src="https://github.com/user-attachments/assets/9ac5c315-8cf5-4271-a2b4-3fc7365391c3" />

<img width="1680" height="1050" alt="Screenshot 2026-11-19 at 2 10 05 PM" src="https://github.com/user-attachments/assets/17396399-9398-4150-b192-f0b980460dcc" />


##  Author

**Ritika Sinha**
  
Indira Gandhi Delhi Technical University for Women (IGDTUW)

---

 StayNest is an Android hotel booking application built using Kotlin, Jetpack Compose, and Firebase to explore modern mobile development and backend integration.
