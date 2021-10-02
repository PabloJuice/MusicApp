package com.pablojuice.musicapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pablojuice.musicapp.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMainBinding;
import com.pablojuice.musicapp.ui.adatpers.MusicListAdapter;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainViewModel viewModel;
    private MusicListAdapter adapter;

    @Override
    protected FragmentMainBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupViewModel();
        setupObservers();
    }

    private void setupViewModel(){
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.loadMusicRequest(requireActivity());
    }

    private void setupObservers(){
        viewModel.getMusicItems().observe(getViewLifecycleOwner(), response ->{
            if (response != null){
                adapter.setItems(response);
                binding.musicRecyclerView.setAdapter(adapter);
            }
        });
    }

    private void setupRecyclerView(){
        adapter = new MusicListAdapter(null);
        binding.musicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}