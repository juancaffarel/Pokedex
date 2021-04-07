package com.juancaffarel.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juancaffarel.pokedex.game.GameAdapter;
import com.juancaffarel.pokedex.network.PokeCallBack;
import com.juancaffarel.pokedex.network.PokemonLoader;
import com.juancaffarel.pokedex.network.models.Abilities;
import com.juancaffarel.pokedex.network.models.Ability;
import com.juancaffarel.pokedex.network.models.PokemonByIdResponse;
import com.juancaffarel.pokedex.pokemon.PokemonAdapter;
import com.juancaffarel.pokedex.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends BaseActivity {

    TextView tvPokemonTitle;
    ImageView ivPokeSprite;
    TextView tvPokeXp;
    TextView tvPokeAbilities;
    RecyclerView rvPokeGames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);


        tvPokemonTitle = findViewById(R.id.tvPokeTitle);
        ivPokeSprite = findViewById(R.id.ivPokeSprite);
        tvPokeXp = findViewById(R.id.tvPokeXp);
        tvPokeAbilities = findViewById(R.id.tvPokeAbilities);
        rvPokeGames = findViewById(R.id.rvPokeGames);


        String pokemonId = getIntent().getStringExtra(Constants.EXTRA_POKEMON_ID);

        Call<PokemonByIdResponse> call = loader.getPokemonById(pokemonId);

        call.enqueue(new PokeCallBack<PokemonByIdResponse>(PokemonDetailActivity.this, true) {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {
                    tvPokemonTitle.setText(response.body().getId() + "-" + response.body().getName());
                    tvPokeXp.setText("XP Base: " + response.body().getBaseExperience());

                    Glide.with(PokemonDetailActivity.this).load(response.body().getSprites().getImage()).into(ivPokeSprite);

                    List<Ability> abilityList = new ArrayList<>();

                    StringBuilder sb = new StringBuilder();

                    for (Abilities abilities : response.body().getAbilities()) {
                        abilityList.add(abilities.getAbility());
                    }
                    for (Ability ability : abilityList) {
                        sb.append(ability.getName() + "\n");
                    }

                    GameAdapter adapter = new GameAdapter(response.body().getGames());

                    rvPokeGames.setAdapter(adapter);

                    rvPokeGames.setHasFixedSize(true);

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PokemonDetailActivity.this);

                    rvPokeGames.setLayoutManager(manager);
                }
                    else{
                    showDialogError();
                }

            }

            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                super.onFailure(call, t);
                showDialogError();
            }
        });


    }
}
