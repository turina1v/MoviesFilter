package com.turina1v.moviesfilter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turina1v.moviesfilter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PosterAdapter()
        recycler_posters.adapter = adapter
        recycler_posters.addItemDecoration(GridItemDecoration(3, 16))
    }
}
