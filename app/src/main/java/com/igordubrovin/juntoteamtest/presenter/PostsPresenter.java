package com.igordubrovin.juntoteamtest.presenter;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.presenter.presenter_interface.IPostsPresenter;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.view.view_interface.IPostsView;

import javax.inject.Inject;

/**
 * Created by Ксения on 20.05.2017.
 */

public class PostsPresenter extends MvpBasePresenter<IPostsView>
        implements IPostsPresenter {

    private PrefManager prefManager;
    private String category;

    @Inject
    PostsPresenter(PrefManager prefManager){
        this.prefManager = prefManager;
    }

    @Override
    public void attachView(IPostsView view) {
        super.attachView(view);
        if (category != null){
            getView().showCategory(category);
            category = null;
        }
    }

    @Override
    public void getPosts() {

    }

    @Override
    public void getCategory() {
        new CategoryTask().execute();
    }

    private void showCategory(String category){
        if (category == null)
            category = ProjectConstants.TECH_CATEGORY;
        if (isViewAttached()){
            getView().showCategory(category);
        } else {
            this.category = category;
        }
    }

    private class CategoryTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            return prefManager.getCategory();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showCategory(s);
        }
    }

}
