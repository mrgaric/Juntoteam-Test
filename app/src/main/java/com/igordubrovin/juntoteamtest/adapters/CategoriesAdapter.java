package com.igordubrovin.juntoteamtest.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.interfaces.OnItemClickListener;
import com.igordubrovin.juntoteamtest.utils.categories.CategoryItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ксения on 20.05.2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<CategoryItem> categoryItems;
    private OnItemClickListener onItemClickListener;

    @Inject
    CategoriesAdapter(){
    };

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItems.get(position);
        holder.tvItemCategory.setText(categoryItem.getName());
        holder.tvItemCategory.setTextColor(Color.parseColor(categoryItem.getColor()));
    }

    @Override
    public int getItemCount() {
        return categoryItems == null ? 0 : categoryItems.size();
    }

    public void setCategoryItems(List<CategoryItem> categoryItems){
        this.categoryItems = categoryItems;
        notifyDataSetChanged();
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_category)
        TextView tvItemCategory;
        public CategoriesViewHolder(View itemView, OnItemClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setClickListener(clickListener);
        }

        protected void setClickListener(final OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onItemClick(getAdapterPosition());
            });
        }
    }
}
