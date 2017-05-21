package com.igordubrovin.juntoteamtest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.igordubrovin.juntoteamtest.utils.CategoryItem;

import java.util.List;

/**
 * Created by Ксения on 21.05.2017.
 */

public class CategoriesFragment  extends Fragment{
    List<CategoryItem> categoryItems;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
    }
}
