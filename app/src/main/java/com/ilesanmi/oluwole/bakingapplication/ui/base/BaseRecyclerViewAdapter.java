package com.ilesanmi.oluwole.bakingapplication.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.reactivex.annotations.NonNull;

public abstract class BaseRecyclerViewAdapter <V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerViewListener.OnItemClickListener itemClickListener;
    private RecyclerViewListener.OnItemLongClickListener itemLongClickListener;

    public void setOnItemClickListener(
            @NonNull RecyclerViewListener.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(
            @NonNull RecyclerViewListener.OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    /**
     * Override onBindViewHolder to support OnItemClick and OnItemLongClick listener.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (itemClickListener != null) {
            viewHolder.itemView.setOnClickListener(view ->
                    itemClickListener.OnItemClick(view, i));
        }
        if (itemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(view -> {
                itemLongClickListener.OnItemLongClick(view, i);
                return true;
            });
        }
    }
}
