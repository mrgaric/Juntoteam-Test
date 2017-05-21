package com.igordubrovin.juntoteamtest.view.activityes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingePostActivity extends AppCompatActivity {
    @BindView(R.id.tv_single_post_desc)
    TextView tvSinglePostDesc;
    @BindView(R.id.tv_single_post_name)
    TextView tvSinglePostName;
    @BindView(R.id.tv_single_post_upvotes)
    TextView tvSinglePostUpvotes;
    @BindView(R.id.btn_get_it)
    Button btnGetIt;
    @BindView(R.id.iv_image_post)
    ImageView ivImagePost;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String postName;
    private String postDesc;
    private String upVotes;
    private String urlImage;
    private String urlPost;
    private Uri uriPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singe_post);
        ButterKnife.bind(this);
        postName = getIntent().getStringExtra(ProjectConstants.POST_NAME);
        postDesc = getIntent().getStringExtra(ProjectConstants.POST_DESC);
        upVotes = getIntent().getStringExtra(ProjectConstants.POST_UP_VOTES);
        urlImage = getIntent().getStringExtra(ProjectConstants.POST_IMAGE_URL);
        urlPost = getIntent().getStringExtra(ProjectConstants.POST_URL_POST);
        uriPost = Uri.parse(urlPost);
        Uri imageUri = Uri.parse(urlImage);
        tvSinglePostName.setText(postName);
        tvSinglePostDesc.setText(postDesc);
        tvSinglePostUpvotes.setText(upVotes);
        Picasso.with(this)
                .load(imageUri)
                .into(ivImagePost);
        initToolbar();
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(postName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @OnClick(R.id.btn_get_it)
    void clickBtnGetIt(){
        Intent intent = new Intent(Intent.ACTION_VIEW, uriPost);
        startActivity(intent);
    }

}
