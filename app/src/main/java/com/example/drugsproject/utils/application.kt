package com.example.drugsproject.utils

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext


@HiltAndroidApp
class Application() : Application() {
    override fun onCreate() {
        super.onCreate()
         FirebaseApp.initializeApp(applicationContext)
    }
}