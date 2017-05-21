package com.igordubrovin.juntoteamtest.view.activityes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.igordubrovin.juntoteamtest.App;
import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.adapters.PostsAdapter;
import com.igordubrovin.juntoteamtest.di.component.PostsComponent;
import com.igordubrovin.juntoteamtest.fragments.CategoriesFragment;
import com.igordubrovin.juntoteamtest.fragments.PostsFragment;
import com.igordubrovin.juntoteamtest.presenter.PostsPresenter;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.utils.posts.PostItem;
import com.igordubrovin.juntoteamtest.utils.posts.Posts;
import com.igordubrovin.juntoteamtest.view.view_interface.IPostsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsActivity extends MvpActivity<IPostsView, PostsPresenter>
        implements IPostsView {

    @BindView(R.id.rv_posts)
    RecyclerView rvPosts;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    PostsPresenter postsPresenter;
    @Inject
    PostsAdapter postsAdapter;
    private PostsFragment postsFragment;

    private PostsComponent postsComponent = App.getPostsComponent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postsComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);
        createPostsFragment();
        initToolbar();
        initRecyclerView(savedInstanceState);
    }

    @NonNull
    @Override
    public PostsPresenter createPresenter() {
        return postsPresenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == ProjectConstants.REQUEST_CODE_CATEGORIES_ACTIVITY){
                showCategory(data.getStringExtra(ProjectConstants.CATEGORY_NAME));
                getPresenter().getPosts(data.getStringExtra(ProjectConstants.CATEGORY_SLUG));
            }
        }
    }

    private void createPostsFragment(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        postsFragment = (PostsFragment) supportFragmentManager.findFragmentByTag(ProjectConstants.POSTS_FRAGMENT_TAG);
        if (postsFragment == null) {
            postsFragment = postsComponent.getPostsFragment();
            supportFragmentManager.beginTransaction()
                    .add(postsFragment, ProjectConstants.POSTS_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getCategory();
    }

    private void initRecyclerView(Bundle savedInstanceState){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postsAdapter.setOnItemClickListener(position -> {

        });
        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setAdapter(postsAdapter);
        if (savedInstanceState == null) {
            getPosts();
        } else {
            postsAdapter.setPostItems(postsFragment.getPostItems());
        }
    }

    private void getPosts(){
        getPresenter().getPosts();
    }

    private void getCategory(){
        getPresenter().getCategoryName();
    }

    @OnClick(R.id.tv_category)
    void clickTvCategory(){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivityForResult(intent, ProjectConstants.REQUEST_CODE_CATEGORIES_ACTIVITY);
    }

    @Override
    public void showPosts(List<PostItem> postItems) {
        postsFragment.setPostItems(postItems);
        postsAdapter.setPostItems(postItems);
    }

    @Override
    public void showError() {
        //TODO
    }

    @Override
    public void showCategory(String category) {
        tvCategory.setText(category);
    }
}
