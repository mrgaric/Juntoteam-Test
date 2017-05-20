package com.igordubrovin.juntoteamtest.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.igordubrovin.juntoteamtest.utils.ProjectConstants;

import javax.inject.Inject;

import static com.igordubrovin.juntoteamtest.utils.ProjectConstants.CATEGORY_NAME;
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

    public String getCategory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CATEGORY_NAME, null);
    }

    public void saveLogin(String category) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(CATEGORY_NAME, category);
        edit.commit();
    }
}
