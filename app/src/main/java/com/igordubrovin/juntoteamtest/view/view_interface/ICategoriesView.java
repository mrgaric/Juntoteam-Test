package com.igordubrovin.juntoteamtest.view.view_interface;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.igordubrovin.juntoteamtest.utils.CategoryItem;

import java.util.List;

/**
 * Created by Ксения on 20.05.2017.
 */

public interface ICategoriesView extends MvpView {
    void showCategories(List<CategoryItem> categoryItems);
    void showError();
}
