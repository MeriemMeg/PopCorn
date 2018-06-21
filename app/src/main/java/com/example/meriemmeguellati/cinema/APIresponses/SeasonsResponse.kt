package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

 class SeasonsResponse {

     @SerializedName("id")
     private var id: Int = 0

     @SerializedName("episode_count")
     private var episode_count: Int = 0

     @SerializedName("poster_path")
     private var poster_path: String = ""

     @SerializedName("season_number")
     private var season_number: Int = 0


 }