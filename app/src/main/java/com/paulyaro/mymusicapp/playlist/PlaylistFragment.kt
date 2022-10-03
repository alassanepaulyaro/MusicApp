package com.paulyaro.mymusicapp.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulyaro.mymusicapp.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

    private lateinit var viewModel : PlaylistViewModel
    private lateinit var viewModelFactory : PlaylistViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:3000/")  // check your localhost ip address or  http://127.0.0.1:3000/
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PlaylistAPI::class.java)

    private val service = PlaylistService(api)

    private val repository = PlaylistRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setUpViewModel()

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull() != null) {
                setUpList(view, playlists.getOrNull()!!)
            } else {
                //TODO
            }
        }
        return view
    }

    private fun setUpList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists)
        }
    }

    private fun setUpViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply { }
    }
}