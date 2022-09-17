package com.example.pokedex.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeApiService {
    @GET
    suspend fun getPokemonList(@Url url: String): Response<PokeResponse>
}