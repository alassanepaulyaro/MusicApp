package com.paulyaro.mymusicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paulyaro.mymusicapp.playlist.PlaylistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}