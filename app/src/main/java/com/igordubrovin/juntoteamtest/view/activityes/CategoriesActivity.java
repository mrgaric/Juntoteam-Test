package com.igordubrovin.juntoteamtest.view.activityes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.igordubrovin.juntoteamtest.App;
import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.adapters.CategoriesAdapter;
import com.igordubrovin.juntoteamtest.di.component.CategoriesComponent;
import com.igordubrovin.juntoteamtest.fragments.CategoriesFragment;
import com.igordubrovin.juntoteamtest.presenter.CategoriesPresenter;
import com.igordubrovin.juntoteamtest.utils.categories.CategoryItem;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.view.view_interface.ICategoriesView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends MvpActivity<ICategoriesView, CategoriesPresenter>
        implements ICategoriesView {
    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_load_categories)
    ProgressBar pbLoadCategories;
    @Inject
    CategoriesAdapter categoriesAdapter;
    @Inject
    CategoriesPresenter categoriesPresenter;
    @Inject
    Context context;
    private boolean loading;
    private CategoriesFragment categoriesFragment;

    private CategoriesComponent categoriesComponent = App.getCategoriesComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        categoriesComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        if (savedInstanceState == null)
            loading = true;
        else
            loading = savedInstanceState.getBoolean(ProjectConstants.CATEGORIES_LOADING);
        ButterKnife.bind(this);
        showProgressBar(loading);
        createCategoriesFragment();
        initToolbar();
        initRecyclerView(savedInstanceState);
    }

    @NonNull
    @Override
    public CategoriesPresenter createPresenter() {
        return categoriesPresenter;
    }

    private void createCategoriesFragment(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        categoriesFragment = (CategoriesFragment) supportFragmentManager.findFragmentByTag(ProjectConstants.CATEGORIES_FRAGMENT_TAG);
        if (categoriesFragment == null) {
            categoriesFragment = categoriesComponent.getCategoriesFragment();
            supportFragmentManager.beginTransaction()
                    .add(categoriesFragment, ProjectConstants.CATEGORIES_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void showProgressBar(boolean show){
        loading = show;
        if (show)
            pbLoadCategories.setVisibility(View.VISIBLE);
        else
            pbLoadCategories.setVisibility(View.GONE);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ProjectConstants.CATEGORIES_ACTIVITY_TITLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initRecyclerView(Bundle savedInstanceState){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCategories.getContext(), layoutManager.getOrientation());
        categoriesAdapter.setOnItemClickListener(position -> {
            CategoryItem categoryItem = categoriesAdapter.getCategoryItems().get(position);
            String categoryName = categoryItem.getName();
            String categorySlug = categoryItem.getSlug();
            getPresenter().setChooseCategory(categoryName, categorySlug);
            Intent data = new Intent();
            data.putExtra(ProjectConstants.CATEGORY_NAME, categoryName);
            data.putExtra(ProjectConstants.CATEGORY_SLUG, categorySlug);
            setResult(RESULT_OK, data);
            finish();
        });
        rvCategories.addItemDecoration(dividerItemDecoration);
        rvCategories.setLayoutManager(layoutManager);
        rvCategories.setAdapter(categoriesAdapter);
        if (savedInstanceState == null) {
            getCategories();
        } else {
            categoriesAdapter.setCategoryItems(categoriesFragment.getCategoryItems());
        }
    }

    private void getCategories(){
        getPresenter().getCategories();
    }

    @Override
    public void showCategories(List<CategoryItem> categoryItems) {
        showProgressBar(false);
        categoriesFragment.setCategoryItems(categoryItems);
        categoriesAdapter.setCategoryItems(categoryItems);
    }

    @Override
    public void showError() {
        showProgressBar(false);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.categories_activity_root), "Error", BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        snackbar.show();
    }
}
