package com.igordubrovin.juntoteamtest.di.component;

import com.igordubrovin.juntoteamtest.di.module.AppModule;
import com.igordubrovin.juntoteamtest.di.module.PostsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ксения on 20.05.2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    PostsComponent plusPostComponent(PostsModule postsModule);
}
