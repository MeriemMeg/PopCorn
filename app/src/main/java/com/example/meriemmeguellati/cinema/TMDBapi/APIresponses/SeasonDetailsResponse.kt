package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

class SeasonDetailsResponse{



    @SerializedName("id")
    var id: Int = 0

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("episodes")
    var episodes: List<EpisodeResponse>?= null


}