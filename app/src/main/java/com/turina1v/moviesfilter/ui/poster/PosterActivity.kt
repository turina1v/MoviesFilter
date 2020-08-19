package com.turina1v.moviesfilter.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.turina1v.moviesfilter.R
import com.turina1v.moviesfilter.ui.GridItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class PosterActivity : AppCompatActivity() {
    lateinit var adapter: PosterAdapter
    lateinit var viewModel: PosterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PosterAdapter()
        recycler_posters.adapter = adapter
        recycler_posters.addItemDecoration(
            GridItemDecoration(
                3,
                16
            )
        )

        viewModel = ViewModelProviders.of(this).get(PosterViewModel::class.java)
        viewModel.moviesLiveData.observe(this, Observer { movies ->
            adapter.movies = movies
        })
        viewModel.loaderLiveData.observe(this, Observer { isLoading ->
            //TODO скрыть/показать
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            //TODO показать ошибку
        })

    }


}
