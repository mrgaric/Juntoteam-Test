package com.igordubrovin.juntoteamtest.view.view_interface;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.igordubrovin.juntoteamtest.utils.posts.PostItem;

import java.util.List;

/**
 * Created by Ксения on 20.05.2017.
 */

public interface IPostsView extends MvpView {
    void showPosts(List<PostItem> postItems);
    void showError();
    void showCategory(String category);
}
