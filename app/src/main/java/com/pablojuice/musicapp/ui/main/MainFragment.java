package com.pablojuice.musicapp.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pablojuice.musicapp.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainViewModel viewModel;

    @Override
    protected FragmentMainBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }
}