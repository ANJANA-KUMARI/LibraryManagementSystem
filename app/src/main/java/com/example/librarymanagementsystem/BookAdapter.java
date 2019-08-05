package com.example.librarymanagementsystem;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Book bookArray[];
    private Context context;

    public BookAdapter( Book[] data, Context context) {
        super();
        this.bookArray = data;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_view_holder, parent,false);

        BookViewHolder views = new BookViewHolder(layout);
        return views;

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.title.setText(bookArray[position].title);
        Resources resources = context.getResources();
        int coverID = resources.getIdentifier(bookArray[position].cover, "drawable", context.getPackageName());
        holder.cover.setImageResource(coverID);
    }

    @Override
    public int getItemCount() {
        return bookArray.length;
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
