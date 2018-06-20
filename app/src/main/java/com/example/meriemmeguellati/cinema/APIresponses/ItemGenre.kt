package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class ItemGenre {
    @SerializedName("name")
    private var name: String? = null

    @SerializedName("id")
    private var id: Int = 0

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    override fun toString(): String {
        return "ItemGenre{" +
                "name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                "}"
    }
}