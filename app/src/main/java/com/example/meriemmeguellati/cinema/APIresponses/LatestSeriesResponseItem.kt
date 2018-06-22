package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

    class LatestSeriesResponseItem {

        @SerializedName("id")
         var id: Int = 0

        @SerializedName("poster_path")
        var poster_path: String? = ""

        @SerializedName("name")
         var name: String = ""

        @SerializedName("overview")
        var overview: String = ""



    }