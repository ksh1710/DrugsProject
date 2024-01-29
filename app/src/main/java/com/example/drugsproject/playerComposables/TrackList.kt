//package com.example.drugsproject.playerComposables
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.example.drugsproject.models.Track
//
//@Composable
//fun TrackList(
//    isPlaying: MutableState<Boolean>,
//    listState: LazyListState,
//    playingSongIndex: MutableState<Int>,
//    tracks: List<Track>,
//    overLayIcon: Int,
//    onTrackItemClick: (Track) -> Unit
//) {
//    LazyRow(
//        contentPadding = PaddingValues(16.dp),
//        state = listState,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        items(
//            items = tracks,
//            itemContent = {
//                TrackListItem(
//                    track = it,
//                    playingSongIndex,
//                    isPlaying,
//                    overLayIcon,
//                    onTrackItemClick
//                )
//            }
//        )
//
//    }
//}
//
//@Composable
//fun TrackListItem(
//    track: Track,
//    playingSongIndex: MutableState<Int>,
//    isPlaying: MutableState<Boolean>,
//    overLayIcon: Int,
//    onTrackItemClick: (Track) -> Unit
//) {
//    Row(Modifier.clickable(onClick = {onTrackItemClick(track)})) {
//
//    Column {
//    }
//        Box {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current).data(track.img).crossfade(true)
//                    .build(),
//                contentDescription = track.songTitle,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .size(120.dp)
//            )
//            if (playingSongIndex.value == track.index && isPlaying.value) {
//                OverlayRoundedBox(
//                    shape = RoundedCornerShape(12.dp),
//                    color = Color.Gray,
//                    size = 120.dp,
//                    overLayIcon = overLayIcon,
//                    contentDescription = "play indicator"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun OverlayRoundedBox(
//    shape: RoundedCornerShape,
//    color: Color,
//    size: Dp,
//    overLayIcon: Int,
//    contentDescription: String
//) {
//    Box(modifier = Modifier
//        .clip(shape)
//        .background(color)
//        .size(size)) {
//        Image(
//            painter = painterResource(id = overLayIcon),
//            contentDescription = contentDescription,
//            modifier = Modifier.align(
//                Alignment.Center
//            )
//        )
//    }
//}
//
//
