package com.paulyaro.mymusicapp.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PlaylistViewModelFactory @Inject constructor (private val repository: PlaylistRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository) as T
    }

}
