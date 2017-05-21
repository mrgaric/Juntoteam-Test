
package com.igordubrovin.juntoteamtest.utils.posts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("posts")
    @Expose
    private List<PostItem> posts = null;

    public List<PostItem> getPosts() {
        return posts;
    }

    public void setPosts(List<PostItem> posts) {
        this.posts = posts;
    }

}
