package com.example.ramonsl.thetopmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.ramonsl.thetopmovies.Data.Movie
import com.squareup.picasso.Picasso

class MoviesDetailActivity : AppCompatActivity() {
    private val BASE_IMG = "https://image.tmdb.org/t/p/w500/"
    var mTvTitle: TextView? = null
    var mTvVote: TextView? = null
    var mTvOver: TextView? = null
    var mTvLacamento: TextView? = null
    var mIvPoster: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)
        mIvPoster = findViewById(R.id.iv_poster)
        mTvLacamento = findViewById(R.id.tv_lacamento)
        mTvVote = findViewById(R.id.tv_voting)
        mTvOver = findViewById(R.id.tv_overview)
        mTvTitle = findViewById(R.id.tv_title)
        val movie: Movie = intent.getParcelableExtra("movie")
        mTvTitle?.setText(movie.title)
        mTvVote?.setText(movie.vote_average)
        mTvLacamento?.setText(movie.release_date)
        mTvOver?.setText(movie.overview)
        Picasso.get()
                .load(BASE_IMG + movie.poster_path)
                .into(mIvPoster)
    }
}