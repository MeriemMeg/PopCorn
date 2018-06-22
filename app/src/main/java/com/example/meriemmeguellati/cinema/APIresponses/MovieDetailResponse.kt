package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class MovieDetailResponse {

    @SerializedName("original_language")
     var originalLanguage: String? = null

    @SerializedName("imdb_id")
     var imdbId: String? = null

    @SerializedName("video")
     var video: Boolean = false

    @SerializedName("title")
    var title: String? = null

    @SerializedName("backdrop_path")
     var backdropPath: String? = null

    @SerializedName("revenue")
     var revenue: Int = 0

    @SerializedName("genres")
     var genres: List<ItemGenre>? = null

    @SerializedName("popularity")
     var popularity: Double = 0.toDouble()


    @SerializedName("id")
     var id: Int = 0

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("budget")
     var budget: Int = 0

    @SerializedName("overview")
     var overview: String? = null

    @SerializedName("original_title")
     var originalTitle: String? = null

    @SerializedName("runtime")
     var runtime: Int = 0

    @SerializedName("poster_path")
     var posterPath: String? = null


    @SerializedName("release_date")
     var releaseDate: String? = null

    @SerializedName("vote_average")
     var voteAverage: Double = 0.toDouble()

    @SerializedName("belongs_to_collection")
     var collection: Collection? = null

    @SerializedName("tagline")
     var tagline: String? = null

    @SerializedName("adult")
     var adult: Boolean = false

    @SerializedName("homepage")
     var homepage: String? = null

    @SerializedName("status")
     var status: String? = null


}