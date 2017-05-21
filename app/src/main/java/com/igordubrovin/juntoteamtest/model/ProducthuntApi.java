package com.igordubrovin.juntoteamtest.model;

import com.igordubrovin.juntoteamtest.utils.categories.Categories;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;
import com.igordubrovin.juntoteamtest.utils.posts.Posts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Ксения on 20.05.2017.
 */

public interface ProducthuntApi {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer " + ProjectConstants.ACCESS_TOKEN,
            "Host: api.producthunt.com"
    })
    @GET("/v1/categories")
    Call<Categories> getCategories();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: Bearer " + ProjectConstants.ACCESS_TOKEN,
            "Host: api.producthunt.com"
    })
    @GET("/v1/categories/{category}/posts")
    Call<Posts> getPosts(@Path("category") String category);
}
