package com.example.ramonsl.thetopmovies.Data

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie : Parcelable {
    var id: String? = null
    var vote_average: String? = null
    var title: String? = null
    var popularity: String? = null
    var poster_path: String? = null
    var overview: String? = null
    var release_date: String? = null
    var poster: Bitmap? = null

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

    override fun hashCode(): Int {
        return Objects.hash(id, vote_average, title, popularity, poster_path, overview, poster)
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(vote_average)
        dest.writeString(title)
        dest.writeString(popularity)
        dest.writeString(poster_path)
        dest.writeString(overview)
        dest.writeString(release_date)
        dest.writeParcelable(poster, flags)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        vote_average = `in`.readString()
        title = `in`.readString()
        popularity = `in`.readString()
        poster_path = `in`.readString()
        overview = `in`.readString()
        release_date = `in`.readString()
        poster = `in`.readParcelable(Bitmap::class.java.classLoader)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Movie?> = object : Parcelable.Creator<Movie?> {
            override fun createFromParcel(source: Parcel): Movie? {
                return Movie(source)
            }

            override fun newArray(size: Int): Array<Movie?> {
                return arrayOfNulls(size)
            }
        }
    }
}