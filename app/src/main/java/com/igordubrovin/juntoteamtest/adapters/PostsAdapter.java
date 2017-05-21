package com.igordubrovin.juntoteamtest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.utils.PostItem;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Игорь on 17.05.2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<PostItem> postItems;

    @Inject
    PostsAdapter(){

    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return postItems == null ? 0 : postItems.size();
    }

    public void setPosts(List<PostItem> postItems){
        this.postItems = new LinkedList<>(postItems);
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_post_name)
        TextView tvPostName;
        @BindView(R.id.tv_post_description)
        TextView tvPostDescription;
        @BindView(R.id.tv_post_upvotes)
        TextView tvPostUpvotes;
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;
        public PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
