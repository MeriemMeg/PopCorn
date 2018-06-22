package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Network : Serializable {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""
}