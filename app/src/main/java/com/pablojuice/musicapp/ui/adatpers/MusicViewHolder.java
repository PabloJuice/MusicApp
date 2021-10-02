package com.pablojuice.musicapp.ui.adatpers;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.pablojuice.musicapp.model.MusicItem;

public abstract class MusicViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected T binding;

    public MusicViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    protected abstract void bind(MusicItem musicObject);
}
