package com.chingyi.moviedbappcleanarchitecture.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.size.Scale
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.moviedbappcleanarchitecture.R
import com.chingyi.moviedbappcleanarchitecture.binding.DataBoundListAdapter
import com.chingyi.moviedbappcleanarchitecture.databinding.BestPopularMovieItemLayoutBinding
import com.chingyi.moviedbappcleanarchitecture.helper.AppExecutors

class BestPopularMoviesRecyclerAdapter(
    appExecutors: AppExecutors
) : DataBoundListAdapter<Movie,
        BestPopularMovieItemLayoutBinding>(appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.id == newItem.id
        }
    }) {

    var movieItemClick : ((Movie)->Unit)? = null

    override fun createBinding(parent : ViewGroup) : BestPopularMovieItemLayoutBinding {
        val binding = DataBindingUtil.inflate<BestPopularMovieItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.best_popular_movie_item_layout,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.movie?.let { movieItemClick?.invoke(it)  }

        }
        return binding

    }

    override fun bind(binding : BestPopularMovieItemLayoutBinding , item : Movie) {
        binding.movie = item
        binding.bestMovieTitle.text = item.originalTitle ?: item.originalName
        val posterPath = "https://image.tmdb.org/t/p/w500/${item.posterPath}"
        binding.ivBestMovieBackDrop.load(posterPath){
            crossfade(true)
            scale(Scale.FIT)
            placeholder(R.drawable.dummy_profile)
//            transformations(CircleCropTransformation())
        }
        val voteAverage =(item.voteAverage ?: 0.0).toFloat()
        val rating = (voteAverage * 0.5).toFloat()

        binding.ratingBar.apply {
            this.rating = rating
            numStars = 5
        }
    }
}