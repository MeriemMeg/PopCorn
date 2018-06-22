package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import com.google.gson.annotations.SerializedName

 class SeasonsResponse {

     @SerializedName("id")
     var id: Int = 0

     @SerializedName("episode_count")
     var episode_count: Int = 0

     @SerializedName("poster_path")
     var poster_path: String? = ""

     @SerializedName("season_number")
     var season_number: Int = 0


 }