package com.tribalscale.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patel on 6/4/2017.
 *
 * Used as a base RecyclerAdapter
 */

public abstract class BaseRecyclerAdapter<T, E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E> {

    private List<T> mItems;
    protected Context mContext;

    public BaseRecyclerAdapter(Context context){
        mContext = context;
        mItems = new ArrayList<>();
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getItems() {

        if(mItems == null) {
            mItems = new ArrayList<>();
        }

        return mItems;
    }

    public T getItem(int index) {

        if(mItems == null) {
            return null;
        }

        if(index >= mItems.size()) {
            return null;
        }

        return mItems.get(index);
    }

    @Override
    public int getItemCount() {

        if(mItems == null) {
            return 0;
        }

        return mItems.size();
    }

    public void setItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
