package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName
class  VideoResponse {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("results")
    var results: List<VideoResponseItem>?= null

}