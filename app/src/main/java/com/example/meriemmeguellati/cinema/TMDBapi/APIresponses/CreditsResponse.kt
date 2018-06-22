package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses
import com.google.gson.annotations.SerializedName

class CreditsResponse {

    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("cast")
    var cast: List<CastResponse>? = null


}

