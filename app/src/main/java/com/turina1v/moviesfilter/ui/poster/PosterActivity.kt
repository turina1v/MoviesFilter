package com.turina1v.moviesfilter.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.turina1v.moviesfilter.R
import com.turina1v.moviesfilter.ui.GridItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class PosterActivity : AppCompatActivity() {
    lateinit var adapter: PosterAdapter
    lateinit var viewModel: PosterViewModel

    companion object{
        val YEAR_FILTER = 2020
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PosterAdapter()
        recyclerPosters.adapter = adapter
        recyclerPosters.addItemDecoration(
            GridItemDecoration(
                3,
                16
            )
        )

        viewModel = ViewModelProviders.of(this).get(PosterViewModel::class.java)
        viewModel.moviesLiveData.observe(this, Observer { movies ->
            adapter.movies = movies
            recyclerPosters.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            textLoadError.visibility = View.GONE
        })
        viewModel.loaderLiveData.observe(this, Observer { isLoading ->
            if (isLoading){
                progressBar.visibility = View.VISIBLE
                recyclerPosters.visibility = View.GONE
                textLoadError.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            textLoadError.visibility = View.VISIBLE
            textLoadError.text = errorMessage
            progressBar.visibility = View.GONE
            recyclerPosters.visibility = View.GONE
        })

        switchOnly2020.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onYearSwitchCheckedChanged(isChecked, YEAR_FILTER)
        }
    }
}
