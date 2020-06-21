package com.example.ramonsl.thetopmovies.Data

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie() : Parcelable {
    var id: String? = null
    var vote_average: String? = null
    var title: String? = null
    var popularity: String? = null
    var poster_path: String? = null
    var overview: String? = null
    var release_date: String? = null
    var poster: Bitmap? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        vote_average = parcel.readString()
        title = parcel.readString()
        popularity = parcel.readString()
        poster_path = parcel.readString()
        overview = parcel.readString()
        release_date = parcel.readString()
        poster = parcel.readParcelable(Bitmap::class.java.classLoader)
    }

    override fun toString(): String {
        return "Movie{" +
                "id='" + id + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", title='" + title + '\'' +
                ", popularity='" + popularity + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", poster=" + poster +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val movie = o as Movie
        return id == movie.id &&
                vote_average == movie.vote_average &&
                title == movie.title &&
                popularity == movie.popularity &&
                poster_path == movie.poster_path &&
                overview == movie.overview &&
                poster == movie.poster
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(vote_average)
        parcel.writeString(title)
        parcel.writeString(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(release_date)
        parcel.writeParcelable(poster, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}