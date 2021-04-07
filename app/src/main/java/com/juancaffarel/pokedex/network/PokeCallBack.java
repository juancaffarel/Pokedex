package com.juancaffarel.pokedex.network;

import com.juancaffarel.pokedex.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class PokeCallBack<T> implements Callback<T> {
    private BaseActivity activity;
    private boolean progressActivity;

    public PokeCallBack(BaseActivity activity, boolean progressActivity) {
        this.activity = activity;
        this.progressActivity = progressActivity;

        if (progressActivity) activity.showProgress();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (progressActivity) activity.hideProgress();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (progressActivity) activity.hideProgress();
    }
}
