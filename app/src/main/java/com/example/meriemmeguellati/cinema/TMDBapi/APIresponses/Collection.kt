package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

class Collection {
    @SerializedName("backdrop_path")
    private var backdropPath: String? = null

    @SerializedName("name")
    private var name: String? = null

    @SerializedName("id")
    private var id: Int = 0

    @SerializedName("poster_path")
    private var posterPath: String? = null


}