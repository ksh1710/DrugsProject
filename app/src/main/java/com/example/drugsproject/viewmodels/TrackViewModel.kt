//package com.example.drugsproject.viewmodels
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.drugsproject.TrackRepo
//import com.example.drugsproject.models.Track
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class TrackViewModel @Inject constructor(trackRepository:TrackRepo) :ViewModel(){
//
//    private val _trackList =MutableLiveData<List<Track>>()
//    val trackList : LiveData<List<Track>> get() = _trackList
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            trackRepository.getTracks().let {
//                _trackList.postValue(it.sortedBy {track->
//                    track.index
//                })
//            }
//        }
//    }
//}