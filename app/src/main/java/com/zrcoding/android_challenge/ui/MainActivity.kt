package com.zrcoding.android_challenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zrcoding.android_challenge.R
import com.zrcoding.android_challenge.ui.movie.list.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commitNow()
        }
    }
}