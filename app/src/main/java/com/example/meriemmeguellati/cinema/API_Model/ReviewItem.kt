package com.example.meriemmeguellati.cinema.API_Model

import com.google.gson.annotations.SerializedName

class Review {

    @SerializedName("author")
    var author: String? = ""

    @SerializedName("content")
    var content: String? = ""


}