package com.igordubrovin.juntoteamtest.view.activityes;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.igordubrovin.juntoteamtest.App;
import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.adapters.PostsAdapter;
import com.igordubrovin.juntoteamtest.di.component.PostsComponent;
import com.igordubrovin.juntoteamtest.presenter.PostsPresenter;
import com.igordubrovin.juntoteamtest.utils.PostItem;
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

    private PostsComponent postsComponent = ((App)getApplication()).getPostsComponent();
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postsComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);
        initToolbar();
        initRecyclerView();
    }

    @NonNull
    @Override
    public PostsPresenter createPresenter() {
        return postsPresenter;
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tvCategory.setText(category);
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        rvPosts.setLayoutManager(layoutManager);
        rvPosts.setAdapter(postsAdapter);
        getPosts();
    }

    private void getPosts(){
        getPresenter().getPosts();
    }

    @OnClick(R.id.tv_category)
    void clickTvCategory(){
        //TODO
    }

    @Override
    public void showPosts(List<PostItem> postItems) {
        //TODO
    }

    @Override
    public void showError() {
        //TODO
    }
}
