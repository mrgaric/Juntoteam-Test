package com.igordubrovin.juntoteamtest.di.component;

import com.igordubrovin.juntoteamtest.di.module.AppModule;
import com.igordubrovin.juntoteamtest.di.module.CategoriesModule;
import com.igordubrovin.juntoteamtest.di.module.PostsModule;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.model.ProducthuntApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ксения on 20.05.2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    ProducthuntApi getProducthuntApi();
    PrefManager getPrefManager();
    PostsComponent plusPostComponent(PostsModule postsModule);
    CategoriesComponent plusCategoriesComponent(CategoriesModule categoriesModule);
}
