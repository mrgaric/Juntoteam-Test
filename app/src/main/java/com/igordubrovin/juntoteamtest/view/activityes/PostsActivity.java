package com.igordubrovin.juntoteamtest.view.activityes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.igordubrovin.juntoteamtest.App;
import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.adapters.PostsAdapter;
import com.igordubrovin.juntoteamtest.di.component.PostsComponent;
import com.igordubrovin.juntoteamtest.fragments.PostsFragment;
import com.igordubrovin.juntoteamtest.presenter.PostsPresenter;
import com.igordubrovin.juntoteamtest.utils.AlarmHelper;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.utils.posts.PostItem;
import com.igordubrovin.juntoteamtest.view.view_interface.IPostsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsActivity extends MvpActivity<IPostsView, PostsPresenter>
        implements IPostsView,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_posts)
    RecyclerView rvPosts;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.srl_posts)
    SwipeRefreshLayout srlPosts;
    @Inject
    PostsPresenter postsPresenter;
    @Inject
    PostsAdapter postsAdapter;
    @Inject
    Context context;
    private PostsFragment postsFragment;
    private boolean refreshing;

    private PostsComponent postsComponent = App.getPostsComponent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postsComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        if (savedInstanceState == null)
            refreshing = true;
        else
            refreshing = savedInstanceState.getBoolean(ProjectConstants.STATE_REFRESHING_POST_ACTIVITY);
        ButterKnife.bind(this);
        createPostsFragment();
        initToolbar();
        initRefresh(refreshing);
        initRecyclerView(savedInstanceState);
        AlarmHelper.setUpAlarm(context);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ProjectConstants.STATE_REFRESHING_POST_ACTIVITY, refreshing);
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
                setRefreshing(true);
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

    private void initRefresh(boolean refreshing){
        srlPosts.setOnRefreshListener(this);
        srlPosts.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        if (refreshing)
            setRefreshing(true);
    }

    private void setRefreshing(boolean refreshing){
        this.refreshing = refreshing;
        srlPosts.setRefreshing(refreshing);
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
            PostItem postItem = postsAdapter.getPostItems().get(position);
            Intent intent = new Intent(this, SingePostActivity.class);
            intent.putExtra(ProjectConstants.POST_NAME, postItem.getName());
            intent.putExtra(ProjectConstants.POST_DESC, postItem.getTagline());
            intent.putExtra(ProjectConstants.POST_UP_VOTES, String.valueOf(postItem.getVotesCount()));
            intent.putExtra(ProjectConstants.POST_IMAGE_URL, postItem.getScreenshotUrl().get300px());
            intent.putExtra(ProjectConstants.POST_URL_POST, postItem.getRedirectUrl());
            startActivity(intent);
        });
        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setAdapter(postsAdapter);
        if (savedInstanceState == null) {
            getPresenter().getPosts();
        } else {
            postsAdapter.setPostItems(postsFragment.getPostItems());
        }
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
        setRefreshing(false);
        postsFragment.setPostItems(postItems);
        postsAdapter.setPostItems(postItems);
    }

    @Override
    public void showError() {
        setRefreshing(false);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.posts_activity_root), "Error", BaseTransientBottomBar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        snackbar.show();
    }

    @Override
    public void showCategory(String category) {
        tvCategory.setText(category);
    }

    @Override
    public void onRefresh() {
        refreshing = true;
        getPresenter().getPosts();
    }
}
