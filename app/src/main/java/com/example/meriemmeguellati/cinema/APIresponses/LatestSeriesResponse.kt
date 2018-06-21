package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class LatestSeriesResponse {

    @SerializedName("page")
    private var page: Int = 0

    @SerializedName("results")
    private var results: List<LatestSeriesResponseItem>? = null

    fun getResults(): List<LatestSeriesResponseItem>? {
        return results
    }
}