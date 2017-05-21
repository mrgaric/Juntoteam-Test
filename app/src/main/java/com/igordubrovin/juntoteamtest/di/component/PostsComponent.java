package com.igordubrovin.juntoteamtest.di.component;

import com.igordubrovin.juntoteamtest.di.custom_scope.ActivityScope;
import com.igordubrovin.juntoteamtest.di.module.PostsModule;
import com.igordubrovin.juntoteamtest.fragments.PostsFragment;
import com.igordubrovin.juntoteamtest.view.activityes.PostsActivity;

import dagger.Subcomponent;

/**
 * Created by Ксения on 20.05.2017.
 */
@ActivityScope
@Subcomponent(modules = PostsModule.class)
public interface PostsComponent {
    void inject(PostsActivity postsActivity);
    PostsFragment getPostsFragment();
}
