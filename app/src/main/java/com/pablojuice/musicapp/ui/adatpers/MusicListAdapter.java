package com.pablojuice.musicapp.ui.adatpers;

import static com.pablojuice.musicapp.utills.MusicUtil.loadImageFromLink;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablojuice.musicapp.databinding.MusicListItemBinding;
import com.pablojuice.musicapp.model.MusicItem;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder> {

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

    class MusicListViewHolder extends MusicViewHolder<MusicListItemBinding> {

        public MusicListViewHolder(@NonNull MusicListItemBinding binding) {
            super(binding);
        }

        @Override
        protected void bind(MusicItem musicObject) {
            loadImageFromLink(musicObject.getImageSrc(),
                              binding.ivLogo,
                              binding.getRoot().getContext());
            binding.ivContainer.setClipToOutline(true);
            binding.setItem(musicObject);
            binding.getRoot().setOnClickListener(v -> onClickListener.onItemClicked(musicObject));
        }
    }

    public interface MusicListItemClickListener {
        void onItemClicked(MusicItem musicItem);
    }
}
