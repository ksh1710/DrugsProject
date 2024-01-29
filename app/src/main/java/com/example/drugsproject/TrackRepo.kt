//package com.example.drugsproject
//
//import com.example.drugsproject.models.Track
//import com.example.drugsproject.models.toTrack
//import com.example.drugsproject.utils.Constants
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
//import com.google.firebase.storage.storage
//import java.lang.Exception
//import kotlin.concurrent.timerTask
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//
//class TrackRepo {
//    private val storage = Firebase.storage
//    private val albumArt = storage.reference.child(Constants.ALBUMART)
//    private val trackRef = storage.reference
//
//    suspend fun getTracks() = suspendCoroutine<List<Track>> { result ->
//        val trackList = mutableListOf<Track>()
//        try {
//            Firebase.firestore.collection(Constants.TRACKS).get().addOnCompleteListener {
//                var index = 0
//                it.result.forEach { doc ->
//                    val imageUrl = albumArt.child(doc.getString(Constants.ALBUMART) ?: "")
//                    val trackUrl = trackRef.child(doc.getString(Constants.FILENAME) ?: "")
//
//                    imageUrl.downloadUrl.addOnSuccessListener { imgDownloadUrl ->
//                        trackUrl.downloadUrl.addOnSuccessListener { trackDownloadUrl ->
//                            trackList.add(
//                                doc.toTrack(
//                                    index,
//                                    imgDownloadUrl.toString(),
//                                    trackDownloadUrl.toString()
//                                )
//                            )
//                            if (index == it.result.size()-1){
//                                result.resume(trackList)
//                        }
//                            index++
//                    }
//                }
//            }
//        }
//    }
//    catch (e:Exception)
//    {
//        e.printStackTrace()
//    }
//}
//}