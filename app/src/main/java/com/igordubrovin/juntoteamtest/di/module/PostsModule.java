package com.igordubrovin.juntoteamtest.di.module;

import com.igordubrovin.juntoteamtest.adapters.PostsAdapter;
import com.igordubrovin.juntoteamtest.di.custom_scope.ActivityScope;
import com.igordubrovin.juntoteamtest.fragments.PostsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ксения on 20.05.2017.
 */
@Module
public class PostsModule {

    @ActivityScope
    @Provides
    PostsFragment providePostsFragment(){
        return new PostsFragment();
    }
}
