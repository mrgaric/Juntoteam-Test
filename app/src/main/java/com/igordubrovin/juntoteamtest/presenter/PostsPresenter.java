package com.igordubrovin.juntoteamtest.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.presenter.presenter_interface.IPostsPresenter;
import com.igordubrovin.juntoteamtest.view.view_interface.IPostsView;

import javax.inject.Inject;

/**
 * Created by Ксения on 20.05.2017.
 */

public class PostsPresenter extends MvpBasePresenter<IPostsView>
        implements IPostsPresenter {

    private PrefManager prefManager;

    @Inject
    public PostsPresenter(PrefManager prefManager){
        this.prefManager = prefManager;
    }

    @Override
    public void getPosts() {

    }
}
