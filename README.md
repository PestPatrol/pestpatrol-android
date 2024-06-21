![Banner](https://drive.google.com/uc?id=13v4gLZKivlCDC3zjHgKQizo09hUNWZfL)

# Pestpatrol Android

**PestPatrol** is an Android application designed for early detection of rice crop diseases using advanced machine learning models. The Application is built using Kotlin, Jetpack Compose, Dagger-Hilt and Clean Architecture.

## Table of Content
- [Features](#features)
- [Libraries](#libraries)
- [Project Structure](#project-structure)
- [How to Initiate the Project](#how-to-initiate-the-project)

## Features
- Authentication (Login & Register)
- Feature to recognize pest on paddy
- Feature to give recommendation on pest treatment
- Feature to show articles regarding crop pest
- Reminder for doing pest treatment (On Development)
- Chatbot to help user acknowledging pest on paddy interactively (On Development)

## Libraries:
- [Android Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Flow](https://developer.android.com/kotlin/flow)
- [Retrofit](https://square.github.io/retrofit/)
- [Okhttp 3](https://square.github.io/okhttp/)
- [CameraX](https://developer.android.com/training/camerax)
- [Datastore](https://developer.android.com/jetpack/androidx/releases/datastore)
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android#setup)

## Project Structure
- app
- core
  - common
  - feature_api
  - network
- features
  - auth
    - auth_data
    - auth_domain
    - auth_presentation
  - home
    - home_data
    - home_domain
    - home_presentation
  - snap_detection
    - snap_detection_data
    - snap_detection_domain
    - snap_detection_presentation

## How to Initiate the Project
Open this project with Android Studio and add BASE_URL variable (please contact us for this value) in the `local.properties` file.
```properties
sdk.dir=YOUR SDK LOCATION
BASE_URL=PUT THE BASE_URL VALUE HERE
```