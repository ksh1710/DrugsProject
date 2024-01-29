//package com.example.drugsproject.playerComposables
//
//import android.app.Dialog
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.material.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import com.example.drugsproject.models.Track
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TrackDetailsDialog(track: MutableState<Track?>, openDialog: MutableState<Boolean>) {
//    if (openDialog.value && track.value != null) {
//        val track = track.value!!
//        AlertDialog(
//            title = { Text(text = track.songTitle) },
//            text = {
//                Column {
//                    Text(text = track.fileName)
//                    Text(text = (track.index + 1).toString())
//                }
//            },
//            buttons = {
//                Row (horizontalArrangement = Arrangement.Center){
//                    Button(onClick = { openDialog.value = false }) {
//                        Text(text = "Dismiss")
//                    }
//                }
//            },
//            onDismissRequest = { openDialog.value = false }
//        )
//    }
//}