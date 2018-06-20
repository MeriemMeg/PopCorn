package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class ReviewsResponse {


    @SerializedName("id")
    var id:Int? = 0

    @SerializedName("results")
    var results:List<ReviewItem>? = null


}