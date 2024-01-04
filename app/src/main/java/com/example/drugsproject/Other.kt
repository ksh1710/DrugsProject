package com.example.drugsproject

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun OtherScreen(){
    Text(text = "hey whats up other screen", modifier = Modifier
        .fillMaxSize()
        .background(Color.Green))

}