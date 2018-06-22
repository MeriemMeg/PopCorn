package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class SeasonDetailsResponse{



    @SerializedName("id")
    var id: Int = 0

    @SerializedName("episodes")
    var episodes: List<EpisodeResponse>?= null

}