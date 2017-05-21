package com.igordubrovin.juntoteamtest;

import android.app.Application;

import com.igordubrovin.juntoteamtest.di.component.AppComponent;
import com.igordubrovin.juntoteamtest.di.component.CategoriesComponent;
import com.igordubrovin.juntoteamtest.di.component.DaggerAppComponent;
import com.igordubrovin.juntoteamtest.di.component.PostsComponent;
import com.igordubrovin.juntoteamtest.di.module.AppModule;
import com.igordubrovin.juntoteamtest.di.module.CategoriesModule;
import com.igordubrovin.juntoteamtest.di.module.PostsModule;

/**
 * Created by Ксения on 20.05.2017.
 */

public class App extends Application {
    private static AppComponent appComponent;
    private static PostsComponent postsComponent;
    private static CategoriesComponent categoriesComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static PostsComponent getPostsComponent(){
        if (postsComponent == null)
            postsComponent = getAppComponent().plusPostComponent(new PostsModule());
        return postsComponent;
    }

    public static CategoriesComponent getCategoriesComponent(){
        if (categoriesComponent == null)
            categoriesComponent = getAppComponent().plusCategoriesComponent(new CategoriesModule());
        return categoriesComponent;
    }
}
