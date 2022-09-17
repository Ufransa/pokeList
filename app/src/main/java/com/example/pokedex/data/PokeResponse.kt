package com.example.pokedex.data

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    @SerializedName("results") var results: ArrayList<Results?>
)

data class Results(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)