package com.igordubrovin.juntoteamtest.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.model.ProducthuntApi;
import com.igordubrovin.juntoteamtest.presenter.presenter_interface.ICategoriesPresenter;
import com.igordubrovin.juntoteamtest.utils.categories.Categories;
import com.igordubrovin.juntoteamtest.utils.categories.CategoryItem;
import com.igordubrovin.juntoteamtest.view.view_interface.ICategoriesView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ксения on 20.05.2017.
 */

public class CategoriesPresenter extends MvpBasePresenter<ICategoriesView>
        implements ICategoriesPresenter {
    private PrefManager prefManager;
    private ProducthuntApi producthuntApi;
    private List<CategoryItem> categoryItems;
    private Boolean unsuccessful;

    @Inject
    CategoriesPresenter(PrefManager prefManager, ProducthuntApi producthuntApi){
        this.prefManager = prefManager;
        this.producthuntApi = producthuntApi;
    }

    @Override
    public void attachView(ICategoriesView view) {
        super.attachView(view);
        if (categoryItems != null){
            getView().showCategories(categoryItems);
            categoryItems = null;
        }
        if (unsuccessful != null){
            getView().showError();
            unsuccessful = null;
        }
    }

    @Override
    public void getCategories() {
        producthuntApi.getCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.body() != null){
                    showCategory(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                showError(true);
            }
        });
    }

    @Override
    public void setChooseCategory(String category, String categorySlug) {
        prefManager.saveCategoryName(category);
        prefManager.saveCategorySlug(categorySlug);
    }

    private void showCategory(List<CategoryItem> categoryItems){
        if (isViewAttached())
            getView().showCategories(categoryItems);
        else
            this.categoryItems = categoryItems;
    }

    private void showError(boolean unsuccessful){
        if (isViewAttached())
            getView().showError();
        else
            this.unsuccessful = unsuccessful;
    }

}
