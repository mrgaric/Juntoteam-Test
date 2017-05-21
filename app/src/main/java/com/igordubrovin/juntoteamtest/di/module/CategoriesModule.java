package com.igordubrovin.juntoteamtest.di.module;

import com.igordubrovin.juntoteamtest.di.custom_scope.ActivityScope;
import com.igordubrovin.juntoteamtest.fragments.CategoriesFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ксения on 20.05.2017.
 */
@Module
public class CategoriesModule {

    @Provides
    CategoriesFragment provideCategoriesFragment(){
        return new CategoriesFragment();
    }
}
