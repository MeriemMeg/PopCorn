package com.example.meriemmeguellati.cinema.APIresponses


import com.google.gson.annotations.SerializedName

class SerieDetailsResponse {

    @SerializedName("seasons")
     var seasons: List<SeasonsResponse>? = null

    @SerializedName("type")
    var type: String = ""


    @SerializedName("backdrop_path")
    var backdrop_path: String? = ""

    @SerializedName("networks")
    var networks: List<Network>? = null


}