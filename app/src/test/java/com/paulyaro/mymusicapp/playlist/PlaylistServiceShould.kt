package com.paulyaro.mymusicapp.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.paulyaro.mymusicapp.utils.BaseUnitTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.times

class PlaylistServiceShould: BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()

    @Test
    fun fetchPlaylistFromAPI() = runBlockingTest {
        service = PlaylistService(api)
        service.fetchPlaylists()
        verify(api, times(1)).fetchAllPlaylists()

    }
}