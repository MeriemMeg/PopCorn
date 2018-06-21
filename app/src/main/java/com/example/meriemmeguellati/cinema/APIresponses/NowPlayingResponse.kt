package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class NowPlayingResponse {

    @SerializedName("page")
    private var page: Int = 0

    @SerializedName("total_pages")
    private var totalPages: Int = 0

    @SerializedName("results")
    private var results: List<MovieResponse>? = null

    @SerializedName("total_results")
    private var totalResults: Int = 0


    fun getResults(): List<MovieResponse>? {
        return results
    }



}