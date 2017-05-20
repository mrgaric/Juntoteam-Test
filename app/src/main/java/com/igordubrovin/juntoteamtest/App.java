package com.igordubrovin.juntoteamtest;

import android.app.Application;

import com.igordubrovin.juntoteamtest.di.component.AppComponent;
import com.igordubrovin.juntoteamtest.di.component.DaggerAppComponent;
import com.igordubrovin.juntoteamtest.di.component.PostsComponent;
import com.igordubrovin.juntoteamtest.di.module.AppModule;
import com.igordubrovin.juntoteamtest.di.module.PostsModule;

/**
 * Created by Ксения on 20.05.2017.
 */

public class App extends Application {
    private AppComponent appComponent;
    private PostsComponent postsComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getGetAppComponent() {
        return appComponent;
    }

    public PostsComponent getPostsComponent(){
        if (postsComponent == null)
            postsComponent = appComponent.plusPostComponent(new PostsModule());
        return postsComponent;
    }
}
