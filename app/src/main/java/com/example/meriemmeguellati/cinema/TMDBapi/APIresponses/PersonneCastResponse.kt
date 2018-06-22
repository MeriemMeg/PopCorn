package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses
import com.google.gson.annotations.SerializedName

class PersonneCastResponse {

    @SerializedName("cast")
    var cast: List<MovieResponse>? = null

}
