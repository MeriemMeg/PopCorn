package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Salle (
        val nom: String,
        val adresse: String,
        val tel: String,
        val thumbnail: Int
) : Serializable{
}