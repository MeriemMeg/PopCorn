package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName



class GenreResponse {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int = 0


}