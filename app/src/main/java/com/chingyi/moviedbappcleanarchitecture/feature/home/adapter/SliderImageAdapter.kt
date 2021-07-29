package com.chingyi.moviedbappcleanarchitecture.feature.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.Coil
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import coil.util.CoilUtils
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.moviedbappcleanarchitecture.R
import com.smarteist.autoimageslider.SliderViewAdapter
import okhttp3.OkHttpClient
import timber.log.Timber

class SliderImageAdapter: SliderViewAdapter<SliderViewHolder>() {

    var movieList = arrayListOf<Movie>()
    override fun getCount() : Int {
       return movieList.size
    }

    override fun onCreateViewHolder(parent : ViewGroup?) : SliderViewHolder {
       val view = LayoutInflater.from(parent?.context)
           .inflate(R.layout.image_slider_item_layout, null)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder : SliderViewHolder? , position : Int) {
        val item = movieList[position]
        viewHolder?.bindView(movie = item)
    }
}

class SliderViewHolder(itemView : View) : SliderViewAdapter.ViewHolder(itemView) {

    private var ivBackDrop : ImageView
    private var movieTitleTv : TextView
        init {
            super.itemView
            ivBackDrop = itemView.findViewById(R.id.iv_backDrop)
            movieTitleTv = itemView.findViewById(R.id.movie_title)



        }

    @SuppressLint("LogNotTimber")
    fun  bindView(movie : Movie){
        movieTitleTv.text = movie.title

        val posterPath = "https://image.tmdb.org/t/p/original/${movie.backdropPath}"
        Timber.d("image_path %s",posterPath)
        ivBackDrop.load(posterPath) {
            crossfade(true)
//            placeholder(R.drawable.dummy_profile)
//            transformations(CircleCropTransformation())
        }
    }

}