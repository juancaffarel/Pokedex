package com.juancaffarel.pokedex.pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juancaffarel.pokedex.PokemonDetailActivity;
import com.juancaffarel.pokedex.R;
import com.juancaffarel.pokedex.network.models.Games;
import com.juancaffarel.pokedex.network.models.Pokemon;
import com.juancaffarel.pokedex.utils.Constants;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    List<Pokemon> pokemonList;
    Context ctx;

    public PokemonAdapter(List<Pokemon> pokemonList, Context ctx) {
        this.pokemonList = pokemonList;
        this.ctx = ctx;
    }

    public PokemonAdapter(List<Games> games) {
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon,parent,false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int i) {
        holder.tvPokemonName.setText(pokemonList.get(i).getName());

        holder.tvPokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonId = pokemonList.get(holder.getAdapterPosition()).getName();

                Intent intent = new Intent(ctx, PokemonDetailActivity.class);
                intent.putExtra(Constants.EXTRA_POKEMON_ID,pokemonId);
                ctx.startActivity(intent);
            }
        });

        holder.btPokemonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
