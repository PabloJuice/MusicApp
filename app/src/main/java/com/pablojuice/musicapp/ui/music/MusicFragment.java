package com.pablojuice.musicapp.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.pablojuice.musicapp.R;
import com.pablojuice.musicapp.databinding.FragmentMusicFullScreenBinding;
import com.pablojuice.musicapp.ui.core.BaseFragment;
import com.pablojuice.musicapp.ui.core.adapters.MusicFullScreenAdapter;
import com.pablojuice.musicapp.ui.core.animation.DepthPageTransformer;

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
        setupViewModel();
        setupMusicPlayer();
        setupViewPager();
    }

    private void setupViewModel(){
        viewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        viewModel.setItems(MusicFragmentArgs.fromBundle(getArguments()).getMusicItems().getData());
        viewModel.getIsPlayerReady().observeForever(this::togglePlayBtn);
    }

    private void setupViewPager() {
        binding.musicViewPager
                .setAdapter(new MusicFullScreenAdapter(viewModel.getItems()));
        binding.musicViewPager
                .setCurrentItem(
                        MusicFragmentArgs.fromBundle(getArguments()).getItemPosition(), false);
        binding.musicViewPager.setPageTransformer(new DepthPageTransformer());
    }

    private void setupMusicPlayer(){
        viewModel.setupMusicPlayer();
        binding.musicPlayerController.playBtn
                .setOnClickListener(v -> viewModel.togglePlayer());
        binding.musicViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {
                togglePlayBtn(false);
                viewModel.stopPlayer();
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                viewModel
                        .prepareFromUrl(
                                viewModel.getItems().get(position).getMusicSrc());
                super.onPageSelected(position);
            }
        });
    }

    private void togglePlayBtn(boolean isPlayable){
        binding.musicPlayerController.playBtn.setChecked(false);
        binding.musicPlayerController.playBtn.setEnabled(isPlayable);
        binding.musicPlayerController.playBtn
                .setBackgroundTintList(
                        ContextCompat.getColorStateList(getContext(),
                                                        isPlayable ? R.color.green_700:R.color.green_700_transparent));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.releasePlayer();
    }
}
