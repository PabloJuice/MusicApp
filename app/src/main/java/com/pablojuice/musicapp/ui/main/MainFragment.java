package com.pablojuice.musicapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pablojuice.musicapp.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainViewModel viewModel;

    @Override
    protected FragmentMainBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}