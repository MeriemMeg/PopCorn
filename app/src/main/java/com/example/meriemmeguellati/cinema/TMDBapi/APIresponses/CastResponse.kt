package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

class CastResponse {

    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("character")
    var character: String? = null

    @SerializedName("profile_path")
    var profile_path: String? = null

    @SerializedName("name")
    var name: String? = null


}