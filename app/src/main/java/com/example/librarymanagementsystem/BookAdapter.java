package com.example.librarymanagementsystem;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private BookClickListener bookClickListener;

    private ArrayList<Book> bookArray;
    private Context context;

    public BookAdapter(ArrayList<Book> data, Context context, BookClickListener bookClickListener) {
        super();
        this.bookClickListener = bookClickListener;
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
        holder.title.setText(bookArray.get(position).title);
        Resources resources = context.getResources();
        int coverID = resources.getIdentifier(bookArray.get(position).cover, "drawable", context.getPackageName());
        holder.cover.setImageURI(Uri.parse(bookArray.get(position).cover));

        holder.clickListener = bookClickListener;
        holder.bindListeners();

    }

    @Override
    public int getItemCount() {
        return bookArray.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

      public TextView title;
      public ImageView cover;
      public View parent;
      public BookClickListener clickListener;

        public BookViewHolder(View item){
            super(item);
            this.parent = item;
            this.title = (TextView)item.findViewById(R.id.bookTitle);
            this.cover = (ImageView)item.findViewById(R.id.cover);
        }

        public void bindListeners() {
            this.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onBookClick(getAdapterPosition());
                }
            });


        }

    }

    public interface BookClickListener{
        void onBookClick(int position);
    }
}
