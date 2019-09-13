package com.example.booklistactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Config {
    private Context mContext;

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mISBN;
        private TextView mCategory;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item,parent,false));

            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            mAuthor = (TextView) itemView.findViewById(R.id.author_txtView);
            mISBN = (TextView) itemView.findViewById(R.id.isbn_txtView);
            mCategory = (TextView) itemView.findViewById(R.id.category_txtView);

        }

   }
}
