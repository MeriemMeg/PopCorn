package com.example.meriemmeguellati.cinema.APIresponses
import com.google.gson.annotations.SerializedName

class PersonDetailsResponse {

    @SerializedName("biography")
    var biography: String? = null

    @SerializedName("birthday")
    var birthday: String? = null

}