package com.pablojuice.musicapp.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

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

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        viewModel.setItems(MusicFragmentArgs.fromBundle(getArguments()).getMusicItems().getData());
        viewModel.getIsPlayerReady()
                .observeForever(isReady -> toggleBtn(binding.musicPlayerController.playBtn,
                                                     isReady));
    }

    private void setupViewPager() {
        binding.musicViewPager
                .setAdapter(new MusicFullScreenAdapter(viewModel.getItems()));
        binding.musicViewPager
                .setCurrentItem(
                        MusicFragmentArgs.fromBundle(getArguments()).getItemPosition(), false);
        binding.musicViewPager.setPageTransformer(new DepthPageTransformer());
    }

    private void setupMusicPlayer() {
        viewModel.setupMusicPlayer();
        binding.musicPlayerController.playBtn
                .setOnClickListener(v -> viewModel.togglePlayer());

        binding.musicPlayerController.prevBtn
                .setOnClickListener(v -> {
                    changeCurrentItem(-1);
                });
        binding.musicPlayerController.nextBtn
                .setOnClickListener(v -> {
                    changeCurrentItem(1);
                });
        binding.backBtn.setOnClickListener(view -> goBack());
        binding.musicViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset,
                                       int positionOffsetPixels) {
                toggleBtn(binding.musicPlayerController.playBtn, false);
                viewModel.stopPlayer();
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                toggleBtn(binding.musicPlayerController.prevBtn, position > 0);
                toggleBtn(binding.musicPlayerController.nextBtn,
                          position + 1 < binding.musicViewPager.getAdapter().getItemCount());
                viewModel
                        .prepareFromUrl(
                                viewModel.getItems().get(position).getMusicSrc());
                super.onPageSelected(position);
            }
        });
    }

    private void toggleBtn(View view, boolean isEnabled) {
        view.setEnabled(isEnabled);
        if (view instanceof ToggleButton) {
            ((ToggleButton) view).setChecked(false);
            view.setBackgroundTintList(
                    ContextCompat.getColorStateList(getContext(),
                                                    isEnabled ? R.color.green_700 : R.color.green_700_transparent));
        } else if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(
                    ContextCompat.getColor(getContext(),
                                           isEnabled ? R.color.green_700 : R.color.green_700_transparent),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    private void changeCurrentItem(int index) {
        binding.musicViewPager.setCurrentItem(binding.musicViewPager.getCurrentItem() + index);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.releasePlayer();
    }
}
