package com.igordubrovin.juntoteamtest.di.component;

import com.igordubrovin.juntoteamtest.di.custom_scope.ActivityScope;
import com.igordubrovin.juntoteamtest.di.module.CategoriesModule;
import com.igordubrovin.juntoteamtest.view.activityes.CategoriesActivity;

import dagger.Subcomponent;

/**
 * Created by Ксения on 20.05.2017.
 */
@ActivityScope
@Subcomponent(modules = CategoriesModule.class)
public interface CategoriesComponent {
    void inject(CategoriesActivity categoriesActivity);
}
