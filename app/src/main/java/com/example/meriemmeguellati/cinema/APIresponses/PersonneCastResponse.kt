package com.example.meriemmeguellati.cinema.APIresponses
import com.google.gson.annotations.SerializedName

class PersonneCastResponse {

    @SerializedName("cast")
    var cast: List<MovieResponse>? = null

}
