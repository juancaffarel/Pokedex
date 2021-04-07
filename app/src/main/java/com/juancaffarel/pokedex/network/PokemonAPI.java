package com.juancaffarel.pokedex.network;

import com.juancaffarel.pokedex.network.models.PokemonByIdResponse;
import com.juancaffarel.pokedex.network.models.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();

    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);
}
