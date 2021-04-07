package com.juancaffarel.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.juancaffarel.pokedex.network.PokemonLoader;
import com.juancaffarel.pokedex.utils.DialogManager;

public class BaseActivity extends AppCompatActivity {

    public PokemonLoader loader;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        loader = new PokemonLoader();
        progress = new ProgressDialog(this);

    }

    public void showProgress() {
        progress.setCancelable(false);
        progress.setMessage("Cargando");
        progress.show();
    }

    public void hideProgress() {
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }
    public void showDialogError() {
        DialogManager manager = new DialogManager(BaseActivity.this, "Error","Error del servidor!");

        Dialog dialog = manager.buildDialog();

        dialog.show();

        manager.getBtDialog().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}