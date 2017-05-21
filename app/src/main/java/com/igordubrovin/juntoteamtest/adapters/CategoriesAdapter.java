package com.igordubrovin.juntoteamtest.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.utils.CategoryItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ксения on 20.05.2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<CategoryItem> categoryItems;

    @Inject
    CategoriesAdapter(){

    };

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItems.get(position);
        holder.tvItemCategory.setText(categoryItem.getName());
      //  holder.tvItemCategory.setBackgroundColor(Color.parseColor(categoryItem.getColor()));
    }

    @Override
    public int getItemCount() {
        return categoryItems == null ? 0 : categoryItems.size();
    }

    public void setCategoryItems(List<CategoryItem> categoryItems){
        this.categoryItems = categoryItems;
        notifyDataSetChanged();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_category)
        TextView tvItemCategory;
        public CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
