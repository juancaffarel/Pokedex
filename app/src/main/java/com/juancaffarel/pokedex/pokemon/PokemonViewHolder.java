package com.juancaffarel.pokedex.pokemon;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juancaffarel.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    TextView tvPokemonName;
    Button btPokemonDelete;
    LinearLayout llPokemonContainter;

    public PokemonViewHolder(@NonNull View v) {
        super(v);

        tvPokemonName = v.findViewById(R.id.tvPokemonName);
        llPokemonContainter = v.findViewById(R.id.llPokemonContainer);
        btPokemonDelete = v.findViewById(R.id.btPokemonDelete);

    }
}
