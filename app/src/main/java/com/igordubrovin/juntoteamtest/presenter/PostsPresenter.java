package com.igordubrovin.juntoteamtest.presenter;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.model.ProducthuntApi;
import com.igordubrovin.juntoteamtest.presenter.presenter_interface.IPostsPresenter;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.utils.posts.PostItem;
import com.igordubrovin.juntoteamtest.utils.posts.Posts;
import com.igordubrovin.juntoteamtest.view.view_interface.IPostsView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ксения on 20.05.2017.
 */

public class PostsPresenter extends MvpBasePresenter<IPostsView>
        implements IPostsPresenter {

    private PrefManager prefManager;
    private ProducthuntApi producthuntApi;
    private String category;
    private List<PostItem> postItems;

    @Inject
    PostsPresenter(PrefManager prefManager, ProducthuntApi producthuntApi){
        this.prefManager = prefManager;
        this.producthuntApi = producthuntApi;
    }

    @Override
    public void attachView(IPostsView view) {
        super.attachView(view);
        if (category != null){
            getView().showCategory(category);
            category = null;
        }
        if (postItems != null){
            getView().showPosts(postItems);
            postItems = null;
        }
    }

    @Override
    public void getPosts(String categoryItemName) {
        loadPosts(categoryItemName);
    }

    @Override
    public void getPosts() {
        new CategorySlugTask().execute();
    }

    private void loadPosts(String category){
        producthuntApi.getPosts(category).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.body() != null){
                    showPosts(response.body().getPosts());
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCategoryName() {
        new CategoryNameTask().execute();
    }

    private void showPosts(List<PostItem> postItems){
        if (isViewAttached())
            getView().showPosts(postItems);
        else
            this.postItems = postItems;
    }

    private void showCategoryName(String category){
        if (category == null)
            category = ProjectConstants.TECH_CATEGORY;
        if (isViewAttached()){
            getView().showCategory(category);
        } else {
            this.category = category;
        }
    }

    private class CategoryNameTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            return prefManager.getCategoryName();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showCategoryName(s);
        }
    }

    private class CategorySlugTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            return prefManager.getCategorySlug();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null)
                s = "tech";
            loadPosts(s);
        }
    }

}
