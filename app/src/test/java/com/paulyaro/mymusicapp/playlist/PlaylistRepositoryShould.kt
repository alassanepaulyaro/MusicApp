package com.paulyaro.mymusicapp.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.paulyaro.mymusicapp.utils.BaseUnitTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistRepositoryShould: BaseUnitTest() {
    private val service : PlaylistService = mock()

    @Test
    fun getPlaylistFromService() = runBlockingTest {

        val repository = PlaylistRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }
}