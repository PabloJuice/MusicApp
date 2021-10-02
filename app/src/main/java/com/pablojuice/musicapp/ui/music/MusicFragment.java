package com.pablojuice.musicapp.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pablojuice.musicapp.ui.animation.DepthPageTransformer;
import com.pablojuice.musicapp.ui.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMusicFullScreenBinding;
import com.pablojuice.musicapp.ui.adatpers.MusicFullScreenAdapter;

public class MusicFragment extends BaseFragment<FragmentMusicFullScreenBinding> {

    private MusicViewModel viewModel;

    @Override
    protected FragmentMusicFullScreenBinding bindLayout(LayoutInflater inflater,
                                                        ViewGroup container) {
        return FragmentMusicFullScreenBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        binding.musicViewPager
                .setAdapter(new MusicFullScreenAdapter(
                        MusicFragmentArgs.fromBundle(getArguments()).getMusicItems().getData()));
        binding.musicViewPager
                .setCurrentItem(
                        MusicFragmentArgs.fromBundle(getArguments()).getItemPosition());
        binding.musicViewPager.setPageTransformer(new DepthPageTransformer());
    }
}
