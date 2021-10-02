package com.pablojuice.musicapp.ui.adatpers;

import static com.pablojuice.musicapp.utills.MusicUtil.loadImageFromLink;

import android.content.Context;

import androidx.annotation.NonNull;

import com.pablojuice.musicapp.databinding.MusicListItemBinding;
import com.pablojuice.musicapp.model.MusicItem;

public class MusicListViewHolder extends MusicViewHolder<MusicListItemBinding> {

    public MusicListViewHolder(@NonNull MusicListItemBinding binding) {
        super(binding);
    }

    @Override
    protected void bind(MusicItem musicObject) {
        loadImageFromLink(musicObject.getImageSrc(), binding.ivLogo, binding.getRoot().getContext());
        binding.ivContainer.setClipToOutline(true);
        binding.setItem(musicObject);
    }
}
