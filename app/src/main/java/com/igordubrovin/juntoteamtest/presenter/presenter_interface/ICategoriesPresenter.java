package com.igordubrovin.juntoteamtest.presenter.presenter_interface;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.igordubrovin.juntoteamtest.view.view_interface.ICategoriesView;

/**
 * Created by Ксения on 20.05.2017.
 */

public interface ICategoriesPresenter extends MvpPresenter<ICategoriesView> {
    void getCategories();
}
