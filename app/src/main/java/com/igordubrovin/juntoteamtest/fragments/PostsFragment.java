package com.igordubrovin.juntoteamtest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.igordubrovin.juntoteamtest.utils.posts.PostItem;

import java.util.List;

/**
 * Created by Ксения on 21.05.2017.
 */

public class PostsFragment extends Fragment {
    List<PostItem> postItems;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public List<PostItem> getPostItems() {
        return postItems;
    }

    public void setPostItems(List<PostItem> postItems) {
        this.postItems = postItems;
    }
}
