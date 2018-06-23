package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("overview")
     var overview: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
     var originalTitle: String? = null

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("title")
    var title: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null

    @SerializedName( "genres")
    var genres : List<GenreResponse>? = null

    @SerializedName("poster_path")
     var posterPath: String? = null

    @SerializedName("backdrop_path")
   var backdropPath: String? = null

    @SerializedName("release_date")
     var releaseDate: String? = null

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("popularity")
    var popularity: Double = 0.toDouble()

    @SerializedName("id")
     var id: Int = 0

    @SerializedName("adult")
     var adult: Boolean = false

    @SerializedName("vote_count")
    var voteCount: Int = 0


}