package com.example.drugsproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drugsproject.MusicPlayer.MusicPlayerComposable
import com.example.drugsproject.ui.theme.DrugsProjectTheme
import com.google.firebase.FirebaseApp
//import com.example.drugsproject.viewmodels.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    val viewModel:TrackViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        observeViewModel()
    FirebaseApp.initializeApp(this)
        setContent {
            DrugsProjectTheme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    NonSmokingHabitApp()
                    MainScreen()
                }
            }
        }
    }

//    private fun observeViewModel(){
//        viewModel.trackList.observe(this,{  trackList->
//
//        })
//    }
}




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonSmokingHabitApp() {
    val context = LocalContext.current
    val habitManager = remember { NonSmokingHabitManager(context) }
    val currentStreak = habitManager.getNonSmokingHabit().streak

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Non-Smoking Habit Tracker") },
                colors = topAppBarColors(
                    containerColor = Color.Blue
        )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                StreakDisplay(currentStreak)
                Spacer(modifier = Modifier.height(16.dp))
                MarkNonSmokingDayButton {
                    habitManager.markNonSmokingDay()
                }
            }
        }
    )
}

@Composable
fun StreakDisplay(streak: Int) {
    Text(
        text = "Current non-smoking streak: $streak",
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.padding(16.dp)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MarkNonSmokingDayButton(onClick: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                onClick()
                keyboardController?.hide()
            },
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
        ) {
            Text("Mark Non-Smoking Day")
        }
    }
}

