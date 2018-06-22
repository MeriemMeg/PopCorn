package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class EpisodeResponse{

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("air_date")
    var air_date: String = ""

    @SerializedName("episode_number")
    var episode_number: Int = 0

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("still_path")
    var still_path: String = ""


    @SerializedName("guest_stars")
    var guest_stars: List<CastResponse>? = null




}