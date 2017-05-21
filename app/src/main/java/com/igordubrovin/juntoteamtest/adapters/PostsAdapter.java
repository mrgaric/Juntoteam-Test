package com.igordubrovin.juntoteamtest.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.interfaces.OnItemClickListener;
import com.igordubrovin.juntoteamtest.utils.CategoryItem;
import com.igordubrovin.juntoteamtest.utils.posts.PostItem;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

/**
 * Created by Игорь on 17.05.2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<PostItem> postItems;
    private OnItemClickListener onItemClickListener;
    private Context context;

    @Inject
    PostsAdapter(Context context){
        this.context = context;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        PostItem postItem = postItems.get(position);
        holder.tvPostName.setText(postItem.getName());
        holder.tvPostDescription.setText(postItem.getTagline());
        holder.tvPostUpvotes.setText(String.valueOf(postItem.getVotesCount()));
        Uri uri = Uri.parse(postItem.getThumbnail().getImageUrl());
        Picasso.with(context)
                .load(uri)
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return postItems == null ? 0 : postItems.size();
    }

    public void setPostItems(List<PostItem> postItems){
        this.postItems = postItems;
        notifyDataSetChanged();
    }

    public List<PostItem> getCategoryItems() {
        return postItems;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        public PostsViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setClickListener(listener);
        }

        protected void setClickListener(final OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onItemClick(getAdapterPosition());
            });
        }
    }
}
