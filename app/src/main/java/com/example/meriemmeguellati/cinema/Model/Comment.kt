package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable


data class Comment (
        val id: Int,
        val objet: String,
        val message: String,
        val profile: Int,
        val nom: String,
        val rating: Int
) : Serializable{
}