package com.chingyi.moviedbappcleanarchitecture.feature.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asFlow
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.size.Scale
import com.chingyi.common.helper.Coroutine
import com.chingyi.domain.genre.model.MovieGenre
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.moviedbappcleanarchitecture.R
import com.chingyi.moviedbappcleanarchitecture.binding.DataBoundListAdapter
import com.chingyi.moviedbappcleanarchitecture.databinding.BestPopularMovieItemLayoutBinding
import com.chingyi.moviedbappcleanarchitecture.databinding.GenreTitleItemLayoutBinding
import com.chingyi.moviedbappcleanarchitecture.feature.home.HomeViewModel
import com.chingyi.moviedbappcleanarchitecture.feature.home.listing.GenreViewItem
import com.chingyi.moviedbappcleanarchitecture.helper.AppExecutors
import kotlinx.coroutines.flow.collect

class GenreListAdapter (private val viewModel : HomeViewModel,
                        appExecutors: AppExecutors
) : DataBoundListAdapter<GenreViewItem ,
        GenreTitleItemLayoutBinding>(appExecutors = appExecutors,
diffCallback = object : DiffUtil.ItemCallback<GenreViewItem>() {
    override fun areItemsTheSame(oldItem: GenreViewItem , newItem: GenreViewItem): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: GenreViewItem , newItem: GenreViewItem): Boolean {
        return oldItem.name == newItem.name
                && oldItem.id == newItem.id
    }
}) {

    var genreItemClick : ((GenreViewItem)->Unit)? = null

    @SuppressLint("LogNotTimber")
    override fun createBinding(parent : ViewGroup) : GenreTitleItemLayoutBinding {
        val binding = DataBindingUtil.inflate<GenreTitleItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.genre_title_item_layout,
            parent,
            false
        )

        binding.root.setOnClickListener {
        binding?.movieGenre?.let { genreItemClick?.invoke(it)}
        }
        return binding

    }

    override fun bind(binding : GenreTitleItemLayoutBinding , item : GenreViewItem) {
        binding.movieGenre = item
        Coroutine.main {
            viewModel.selectedGenre.asFlow().collect { selectedName->
                binding.view.visibility =
                    if (selectedName == item.name) View.VISIBLE
                    else View.INVISIBLE

            }
        }
    }
}