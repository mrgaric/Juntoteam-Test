package com.igordubrovin.juntoteamtest.di.module;

import android.content.Context;
import android.view.WindowManager;

import com.igordubrovin.juntoteamtest.model.PrefManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
