package com.example.ramonsl.thetopmovies

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ramonsl.thetopmovies.Adapter.MoviesAdapter
import com.example.ramonsl.thetopmovies.Data.Movie
import com.example.ramonsl.thetopmovies.Data.MoviesDTO
import com.example.ramonsl.thetopmovies.Transport.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mList: MoviesDTO? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private var mMovieAdapter: MoviesAdapter? = null
    private var mErrorMessageDisplay: TextView? = null
    private var mLoadingIndicator: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerview_movies)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        mErrorMessageDisplay = findViewById(R.id.tv_error)
        val layoutManager = GridLayoutManager(this, numberOfColumns(), GridLayoutManager.VERTICAL, false)
        mRecyclerView?.setLayoutManager(layoutManager)
        mRecyclerView?.setHasFixedSize(true)
        mMovieAdapter = MoviesAdapter()
        mRecyclerView?.setAdapter(mMovieAdapter)
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator)
        if (VerificaConexao(this)) {
            popularMovies
        } else {
            showErrorMessage(getString(R.string.error_internet))
        }
    }

    private fun showErrorMessage(msg: String) {
        mRecyclerView!!.visibility = View.INVISIBLE
        mErrorMessageDisplay!!.visibility = View.VISIBLE
        val snackbarError = Snackbar.make(coordinatorLayout!!, msg, Snackbar.LENGTH_LONG)
        snackbarError.show()
    }

    private val popularMovies: Unit
        private get() {
            showProgressBar()
            val call = RetrofitConfig().get().retrievePopularMovies(API_KEY)
            call!!.enqueue(object : Callback<MoviesDTO?> {
                override fun onResponse(call: Call<MoviesDTO?>, response: Response<MoviesDTO?>) {
                    Log.i("CALLBACK", response.message())
                    if (response.isSuccessful == true) {
                        if (response.body() != null) {
                            mList = response.body()
                        }
                        mMovieAdapter!!.setMovieData(mList!!.movies as ArrayList<Movie>?)
                        hideProgressBar()
                    } else {
                        showErrorMessage(getString(R.string.error_indiponivel))
                        Log.i("CALLBACK", response.message())
                    }
                }
                override fun onFailure(call: Call<MoviesDTO?>, t: Throwable) {
                    Log.e("CALLBACK", t.message)
                    showErrorMessage(getString(R.string.error_request))
                }
            })
        }

    private val topRatedMovies: Unit
        private get() {
            showProgressBar()
            val call = RetrofitConfig().get().retriveTopRatedMovies(API_KEY)
            call!!.enqueue(object : Callback<MoviesDTO?> {
                override fun onResponse(call: Call<MoviesDTO?>, response: Response<MoviesDTO?>) {
                    if (response.isSuccessful == true) {
                        if (response.body() != null) {
                            mList = response.body()
                        }
                        mMovieAdapter!!.setMovieData(mList!!.movies as ArrayList<Movie>?)
                        hideProgressBar()
                    } else {
                        showErrorMessage(getString(R.string.error_indiponivel))
                    }
                }

                override fun onFailure(call: Call<MoviesDTO?>, t: Throwable) {
                    Log.e("CALLBACK", t.message)
                    showErrorMessage(getString(R.string.error_request))
                }
            })
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_pop) {
            popularMovies
            hideProgressBar()
            return true
        }
        if (id == R.id.action_top) {
            topRatedMovies
            hideProgressBar()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun numberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthDivider = 400
        val width = displayMetrics.widthPixels
        val nColumns = width / widthDivider
        return if (nColumns < 2) 2 else nColumns
    }

    fun VerificaConexao(contexto: Context): Boolean {
        var conectado = false
        val conmag: ConnectivityManager
        conmag = contexto.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        conmag.activeNetworkInfo
        //Verifica o WIFI
        conectado = if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected) {
            true
        } else if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected) {
            true
        } else {
            false
        }
        return conectado
    }

    private fun showProgressBar() {
        mLoadingIndicator!!.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mLoadingIndicator!!.visibility = View.GONE
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}