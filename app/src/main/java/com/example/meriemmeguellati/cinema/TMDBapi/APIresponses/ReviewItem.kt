package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

class ReviewItem {

    @SerializedName("id")
    var id: String? = ""

    @SerializedName("author")
    var author: String? = ""

    @SerializedName("content")
    var content: String? = ""


}