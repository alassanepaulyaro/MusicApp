package com.paulyaro.mymusicapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel: ViewModel() {
    var playlists = MutableLiveData<Result<List<Playlist>>>()

}
