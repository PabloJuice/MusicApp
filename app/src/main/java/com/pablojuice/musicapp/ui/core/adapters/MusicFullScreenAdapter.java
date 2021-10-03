package com.pablojuice.musicapp.ui.core.adapters;

import static com.pablojuice.musicapp.utills.MusicUtil.loadImageFromLink;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablojuice.musicapp.databinding.MusicFullScreenItemBinding;
import com.pablojuice.musicapp.model.MusicItem;

import java.util.List;

public class MusicFullScreenAdapter extends RecyclerView.Adapter<MusicFullScreenAdapter.MusicFullScreenViewHolder> {

    private final List<MusicItem> items;

    public MusicFullScreenAdapter(List<MusicItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MusicFullScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicFullScreenViewHolder(
                MusicFullScreenItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                                                   parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicFullScreenViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MusicFullScreenViewHolder extends MusicViewHolder<MusicFullScreenItemBinding> {

        public MusicFullScreenViewHolder(@NonNull MusicFullScreenItemBinding binding) {
            super(binding);
        }

        @Override
        protected void bind(MusicItem musicObject) {
            loadImageFromLink(musicObject.getImageSrc(),
                              binding.ivLogo,
                              binding.getRoot().getContext());
            binding.ivContainer.setClipToOutline(true);
            binding.setItem(musicObject);
        }
    }
}
