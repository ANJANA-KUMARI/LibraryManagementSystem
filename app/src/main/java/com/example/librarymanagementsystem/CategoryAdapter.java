package com.example.librarymanagementsystem;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private CategoryClickListener categoryClickListener;


    private Category imageArray[];
    private Context context;

    public CategoryAdapter( Category[] data, Context context,   CategoryClickListener categoryClickListener) {
        super();
        this.categoryClickListener = categoryClickListener;
        this.imageArray = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_holder, parent,false);

        CategoryAdapter.CategoryViewHolder views = new CategoryAdapter.CategoryViewHolder(layout);
        return views;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.catText.setText(imageArray[position].text);
        Resources resources = context.getResources();
        int catImageID = resources.getIdentifier(imageArray[position].image, "drawable", context.getPackageName());
        holder.catImage.setImageResource(catImageID);

        holder.clickListener = categoryClickListener;
        holder.bindListeners();

    }

    @Override
    public int getItemCount() {
        return imageArray.length;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView catText;
        public ImageView catImage;
        public CategoryAdapter.CategoryClickListener clickListener;

        public View parent;

        public CategoryViewHolder(View item){
            super(item);
            parent = item;
            this.catText = (TextView)item.findViewById(R.id.catText);
            this.catImage = (ImageView)item.findViewById(R.id.catImg);
        }

        public void setDetails(Category cat) {
            catText.setText(cat.text);
        }

        public void bindListeners() {
            this.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onCategoryClick(getAdapterPosition());
                }
            });


        }

    }

    public interface CategoryClickListener{
        void onCategoryClick(int position);
    }
}
