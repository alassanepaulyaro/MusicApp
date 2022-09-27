package com.paulyaro.mymusicapp.playlist

import com.paulyaro.mymusicapp.R

data class Playlist(
    val id : String,
    val name :String,
    val category: String,
    val image: Int = R.mipmap.playlist
)
