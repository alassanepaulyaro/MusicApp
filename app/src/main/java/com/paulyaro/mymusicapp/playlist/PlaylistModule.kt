package com.paulyaro.mymusicapp.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistAPI(retrofit: Retrofit) = retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl( "http://192.168.0.20:3000/")  // check your localhost ip address or  http://127.0.0.1:3000/
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}