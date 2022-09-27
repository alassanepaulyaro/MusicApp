package com.paulyaro.mymusicapp.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(private val service: PlaylistService) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> =  service.fetchPlaylists()


}
