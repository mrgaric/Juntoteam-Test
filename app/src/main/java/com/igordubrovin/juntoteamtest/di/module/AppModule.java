package com.igordubrovin.juntoteamtest.di.module;

import android.content.Context;

import com.igordubrovin.juntoteamtest.model.ProducthuntApi;
import com.igordubrovin.juntoteamtest.model.PrefManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ксения on 20.05.2017.
 */

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    PrefManager providePrefManager (Context context){
        PrefManager.newInstance(context);
        return PrefManager.getInstance();
    }

    @Provides
    @Singleton
    ProducthuntApi provideProducthuntApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.producthunt.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ProducthuntApi.class);
    }
}
