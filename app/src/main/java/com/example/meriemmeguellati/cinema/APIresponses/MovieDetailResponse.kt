package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class MovieDetailResponse {

    @SerializedName("original_language")
    private var originalLanguage: String? = null

    @SerializedName("imdb_id")
    private var imdbId: String? = null

    @SerializedName("video")
    private var video: Boolean = false

    @SerializedName("title")
    private var title: String? = null

    @SerializedName("backdrop_path")
    private var backdropPath: String? = null

    @SerializedName("revenue")
    private var revenue: Int = 0

    @SerializedName("genres")
    private var genres: List<ItemGenre>? = null

    @SerializedName("popularity")
    private var popularity: Double = 0.toDouble()


    @SerializedName("id")
    private var id: Int = 0

    @SerializedName("vote_count")
    private var voteCount: Int = 0

    @SerializedName("budget")
    private var budget: Int = 0

    @SerializedName("overview")
    private var overview: String? = null

    @SerializedName("original_title")
    private var originalTitle: String? = null

    @SerializedName("runtime")
    private var runtime: Int = 0

    @SerializedName("poster_path")
    private var posterPath: String? = null


    @SerializedName("release_date")
    private var releaseDate: String? = null

    @SerializedName("vote_average")
    private var voteAverage: Double = 0.toDouble()

    @SerializedName("belongs_to_collection")
    private var collection: Collection? = null

    @SerializedName("tagline")
    private var tagline: String? = null

    @SerializedName("adult")
    private var adult: Boolean = false

    @SerializedName("homepage")
    private var homepage: String? = null

    @SerializedName("status")
    private var status: String? = null


}