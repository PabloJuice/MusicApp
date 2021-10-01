package com.pablojuice.musicapp.ui.adatpers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablojuice.musicapp.databinding.MusicListItemBinding;
import com.pablojuice.musicapp.model.MusicItem;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListViewHolder> {

    private List<MusicItem> items;
    private MusicListItemClickListener onClickListener;

    public MusicListAdapter(MusicListItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MusicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicListViewHolder(
                MusicListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                                             parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<MusicItem> getItems() {
        return items;
    }

    public void setItems(List<MusicItem> items) {
        this.items = items;
    }

    public MusicListItemClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(MusicListItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    interface MusicListItemClickListener {
        void onClick();
    }
}
