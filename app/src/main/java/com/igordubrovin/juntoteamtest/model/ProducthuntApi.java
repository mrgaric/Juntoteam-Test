package com.igordubrovin.juntoteamtest.model;

import com.igordubrovin.juntoteamtest.utils.Categories;
import com.igordubrovin.juntoteamtest.utils.CategoryItem;
import com.igordubrovin.juntoteamtest.utils.ProjectConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

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
}
