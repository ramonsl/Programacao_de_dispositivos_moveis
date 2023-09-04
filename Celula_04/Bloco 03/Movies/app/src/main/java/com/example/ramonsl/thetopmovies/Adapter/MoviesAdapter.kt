package com.example.ramonsl.thetopmovies.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ramonsl.thetopmovies.Adapter.MoviesAdapter.MovieAdapterViewHolder
import com.example.ramonsl.thetopmovies.Data.Movie
import com.example.ramonsl.thetopmovies.MoviesDetailActivity
import com.example.ramonsl.thetopmovies.R
import com.squareup.picasso.Picasso
import java.util.*

class MoviesAdapter : RecyclerView.Adapter<MovieAdapterViewHolder>() {
    private val BASE_IMG = "https://image.tmdb.org/t/p/w500/"
    private var mMoviesData: ArrayList<Movie>? = null
    var context: Context? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieAdapterViewHolder {
        context = viewGroup.context
        val layoutIdForListItens: Int
        layoutIdForListItens = R.layout.movie_list_item_par
        Log.e("COUNT", i.toString())
        val inflater = LayoutInflater.from(context)
        val sholdAttach = false
        val view = inflater.inflate(layoutIdForListItens, viewGroup, sholdAttach)
        return MovieAdapterViewHolder(view)
    }

    override fun onBindViewHolder(movieAdapterViewHolder: MovieAdapterViewHolder, i: Int) {
        Log.e("COUNT2", i.toString())
        val title = mMoviesData!![i].title.toString()
        val rate = mMoviesData!![i].vote_average.toString()
        movieAdapterViewHolder.mMovieTitle.text = title
        Picasso.get()
                .load(BASE_IMG + mMoviesData!![i].poster_path)
                .into(movieAdapterViewHolder.mIvPoster)
    }

    override fun getItemCount(): Int {
        return if (mMoviesData == null) 0 else mMoviesData!!.size
    }

    inner class MovieAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val mMovieTitle: TextView
        val mIvPoster: ImageView
        override fun onClick(v: View) {
            val intent = Intent(v.context, MoviesDetailActivity::class.java)
            val movie = mMoviesData!![adapterPosition]
            intent.putExtra("movie", movie)
            context!!.startActivity(intent)
        }

        init {
            mMovieTitle = view.findViewById(R.id.tv_movie_title)
            mIvPoster = view.findViewById(R.id.iv_poster)
            view.setOnClickListener(this)
        }
    }

    fun setMovieData(data: ArrayList<Movie>?) {
        mMoviesData = data
        notifyDataSetChanged()
    }
}