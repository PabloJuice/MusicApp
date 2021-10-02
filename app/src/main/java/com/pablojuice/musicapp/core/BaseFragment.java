package com.pablojuice.musicapp.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    protected B binding;
    protected NavController navController;

    protected abstract B bindLayout(LayoutInflater inflater, ViewGroup container);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = bindLayout(inflater, container);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(binding.getRoot());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            binding.unbind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goBack() {
        navController.popBackStack();
    }
}
