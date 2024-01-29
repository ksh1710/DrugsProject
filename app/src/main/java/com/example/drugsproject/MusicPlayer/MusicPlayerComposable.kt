package com.example.drugsproject.MusicPlayer

import Category
import Song
import android.annotation.SuppressLint
import android.media.MediaParser
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.drugsproject.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.io.IOException

@Preview
@Composable
fun MusicPlayerComposable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            text = "Meditation music player"
        )
        Text(
            text = "Categories",
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )
        CategoryList()
    }
}


//@Composable
//fun CategoriesRow() {
//    LazyRow(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        for (i in 1..10) {
//            item {
//                Card(
//                    border = BorderStroke(0.5.dp, Color.Black),
//                    modifier = Modifier
//                        .height(125.dp)
//                        .width(125.dp)
//                        .padding(start = 5.dp, end = 5.dp),
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.test),
//                        contentDescription = null
//                    )
//                    Text(text = "Rap")
//                }
//            }
//        }
//    }
//}


@SuppressLint("RememberReturnType")
@Composable
fun CategoryList() {
    val firestore = FirebaseFirestore.getInstance()
    val categoriesCollection = firestore.collection("categories")
    val categories = remember { mutableStateListOf<Category>() }
    val selectedCategory = remember { mutableStateOf<Category?>(null) }
    val songsCollection = firestore.collection("songs") // Assuming a separate collection for songs


    DisposableEffect(Unit) {
        val listenerRegistration = categoriesCollection.addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("Firestore", "Error fetching categories: $error")
                return@addSnapshotListener
            }
            categories.clear()
            value?.documents?.forEach { categoryDocument ->
                val categoryName = categoryDocument.getString("title") ?: ""
//                val songsArray = categoryDocument.get("songarray") as? List<HashMap<String, Any>> ?: emptyList()
                val songNames = categoryDocument.get("songArray") as? List<String> ?: emptyList()
                val coverUrl = categoryDocument.getString("coverUrl") ?: "" // Fetch coverUrl
                Log.d("catName", categoryName)
                Log.d("catName", songNames.toString())
//                val songList = songsArray.map { songMap ->
//                    val title = songMap["title"] as? String ?: ""
//                    val songUrl = songMap["songUrl"] as? String ?: ""
//                    Song(title, songUrl)
//                }

                categories.add(Category(coverUrl, categoryName, songNames))
            }
        }
        onDispose {
            listenerRegistration.remove()
        }
    }
    var songTitle by remember { mutableStateOf("") }
    var songUrl by remember { mutableStateOf("") }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Black)
    ) {
        items(categories) { category ->

            Card(
                shape = RoundedCornerShape(20.dp), modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        // Set the selected category on click
                        selectedCategory.value = category
                    },
                colors = CardDefaults.cardColors(containerColor = Color.Yellow)
            ) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = category.coverUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            scale(Scale.FILL)
                        }).build()
                )
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painter,
                    contentDescription = null
                )
//                Text(text = category.title)
                Text(color = Color.Black, text = category.title)
                Log.d("idk", category.coverUrl)
            }
        }
    }
    selectedCategory.value?.let { selCategory ->
        LazyColumn() {
            items(selCategory.songArray) { song ->
                LaunchedEffect(Unit) {
                    val songDocument = songsCollection.document(song).get().await()
                    Log.d("idkk", songDocument.toString())
                    if (songDocument.exists()) {
                        songTitle = songDocument.getString("title") ?: ""
                        songUrl = songDocument.getString("songUrl") ?: ""
                    }
                }
                Card(
                    border = BorderStroke(0.5.dp, Color.Black),
                    modifier = Modifier
                        .height(125.dp)
                        .width(125.dp)
                        .padding(start = 5.dp, end = 5.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test),
                        contentDescription = null
                    )
                    Text(text = songTitle)
                    Button(onClick = { playSong(songUrl) }) {
                        Text("Play $songTitle")
                    }
                }
            }
        }
    }
}


fun playSong(songUrl: String) {
    val mediaPlayer = MediaPlayer()
    try {
        mediaPlayer.setDataSource(songUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()

        // Optionally, you can handle playback completion or errors
        mediaPlayer.setOnCompletionListener { mp ->
            // Handle playback completion
            mp.release()
        }

        mediaPlayer.setOnErrorListener { mp, what, extra ->
            // Handle playback error
            mp.release()
            true
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


