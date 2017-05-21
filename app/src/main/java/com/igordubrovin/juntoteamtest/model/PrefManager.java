package com.igordubrovin.juntoteamtest.model;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import static com.igordubrovin.juntoteamtest.utils.ProjectConstants.CATEGORY_SLUG;
import static com.igordubrovin.juntoteamtest.utils.ProjectConstants.CATEGORY_NAME;
import static com.igordubrovin.juntoteamtest.utils.ProjectConstants.NUMBER_POSTS;
import static com.igordubrovin.juntoteamtest.utils.ProjectConstants.PREF_FILE;

/**
 * Created by Ксения on 20.05.2017.
 */

public class PrefManager {
    private static PrefManager instance;

    private final Context context;

    private PrefManager(Context context) {
        this.context = context;
    }

    @Inject
    public static void newInstance(Context context) {
        instance = new PrefManager(context);
    }

    public static PrefManager getInstance() {
        return instance;
    }

    public String getCategoryName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CATEGORY_NAME, null);
    }

    public String getCategorySlug(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CATEGORY_SLUG, null);
    }

    public int getNumberPosts(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(NUMBER_POSTS, 0);
    }

    private SharedPreferences.Editor getEditor(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    public void saveCategoryName(String category) {
        SharedPreferences.Editor edit = getEditor();
        edit.putString(CATEGORY_NAME, category);
        edit.commit();
    }

    public void saveCategorySlug(String categoryItemName){
        SharedPreferences.Editor edit = getEditor();
        edit.putString(CATEGORY_SLUG, categoryItemName);
        edit.commit();
    }

    public void saveNumberPosts(int number){
        SharedPreferences.Editor edit = getEditor();
        edit.putInt(NUMBER_POSTS, number);
        edit.commit();
    }
}
