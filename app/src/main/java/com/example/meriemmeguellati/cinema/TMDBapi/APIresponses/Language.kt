package com.example.meriemmeguellati.cinema.TMDBapi.APIresponses

import java.util.*

class Language {
    fun Country(): String {
        var country = Locale.getDefault().country.toLowerCase()

        when (country) {
            "id" -> {
            }

            else -> country = "en"
        }

        return country
    }
}