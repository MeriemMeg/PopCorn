package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName


class PersonneCastResponseItem {

    @SerializedName("credit_id")
    var credit_id: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("title")
    var title: String? = null
}