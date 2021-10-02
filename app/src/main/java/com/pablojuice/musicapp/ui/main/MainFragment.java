package com.pablojuice.musicapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pablojuice.musicapp.ui.core.BaseFragment;
import com.pablojuice.musicapp.databinding.FragmentMainBinding;
import com.pablojuice.musicapp.model.MusicItem;
import com.pablojuice.musicapp.ui.adapters.MusicListAdapter;

public class MainFragment extends BaseFragment<FragmentMainBinding> implements MusicListAdapter.MusicListItemClickListener {

    private MainViewModel viewModel;
    private MusicListAdapter adapter;

    @Override
    protected FragmentMainBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupRecyclerView();
        setupObservers();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.loadMusicRequest(requireActivity());
    }

    private void setupObservers() {
        viewModel.getMusicItemsDto().observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                adapter.setItems(response.getData());
                binding.musicRecyclerView.setAdapter(adapter);
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new MusicListAdapter(this);
        binding.musicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.musicRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                                                                              DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onItemClicked(MusicItem musicItem) {
        navController.navigate(
                MainFragmentDirections.actionMainFragmentToMusicFragment(
                        viewModel.getMusicItemsDto().getValue(),
                        adapter.getItems().indexOf(musicItem))
        );
    }
}