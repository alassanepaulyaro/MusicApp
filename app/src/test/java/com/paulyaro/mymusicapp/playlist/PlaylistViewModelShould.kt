package com.paulyaro.mymusicapp.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.paulyaro.mymusicapp.utils.BaseUnitTest
import com.paulyaro.mymusicapp.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository : PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val execption = java.lang.RuntimeException("Something went wrong")


    @Test
    fun getPlaylistFromRepository()  = runBlockingTest{
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(execption))
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        assertEquals(execption, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlockingTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        return viewModel
    }
}