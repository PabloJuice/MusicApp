package com.pablojuice.musicapp.ui.music;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pablojuice.musicapp.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMusicFullScreenBinding;

public class MusicFragment extends BaseFragment<FragmentMusicFullScreenBinding> {
    @Override
    protected FragmentMusicFullScreenBinding bindLayout(LayoutInflater inflater,
                                                        ViewGroup container) {
        return FragmentMusicFullScreenBinding.inflate(inflater, container, false);
    }


}
