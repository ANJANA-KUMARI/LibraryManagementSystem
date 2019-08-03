package com.example.librarymanagementsystem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public BookAdapter(Context context, int resource, Book[] data) {
        super();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

      public TextView title;
      public ImageView cover;

        public BookViewHolder(View item){
            super(item);
            this.title = (TextView)item.findViewById(R.id.bookTitle);
            this.cover = (ImageView)item.findViewById(R.id.cover);
        }

        public void setDetails(Book book) {
            title.setText(book.title);


        }

    }
}
