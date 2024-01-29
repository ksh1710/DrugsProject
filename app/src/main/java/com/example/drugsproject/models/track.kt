//package com.example.drugsproject.models
//
//import com.example.drugsproject.utils.Constants
//import com.google.firebase.firestore.QueryDocumentSnapshot
//import com.google.firestore.admin.v1.Index
//
//data class Track(
//    val img: String,
//    val index: Int,
//    val songTitle: String,
//    val trackUrl: String,
//    var isPlaying: Boolean,
//    var fileName: String
//)
//
//
//fun QueryDocumentSnapshot.toTrack(index: Int, imgUrl: String, trackUrl: String): Track {
//    return Track(
//        img = imgUrl,
//        songTitle = this.getString(Constants.NAME) ?: "",
//        fileName = this.getString(Constants.FILENAME) ?: "",
//        isPlaying = false,
//        index = index,
//        trackUrl = trackUrl
//    )
//}

data class Song(
    val title: String,
    val songUrl: String
)

data class Category(
    val coverUrl:String,
    val title: String,
    val songArray: List<String>
)
