package com.pablojuice.musicapp.ui.adatpers;

import androidx.annotation.NonNull;

import com.pablojuice.musicapp.databinding.MusicListItemBinding;
import com.pablojuice.musicapp.model.MusicItem;

public class MusicListViewHolder extends MusicViewHolder<MusicListItemBinding> {

    public MusicListViewHolder(@NonNull MusicListItemBinding binding) {
        super(binding);
    }

    @Override
    protected void bind(MusicItem musicObject) {
        binding.setItem(musicObject);
    }
}
