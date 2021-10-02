package com.pablojuice.musicapp.ui.main;

import static com.pablojuice.musicapp.utills.MusicUtil.getJsonFromAsset;
import static com.pablojuice.musicapp.utills.MusicUtil.getMusicFromJson;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pablojuice.musicapp.model.MusicItem;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final String FILE_NAME = "response.json";

    private final MutableLiveData<List<MusicItem>> musicItems = new MutableLiveData<>();

    public void loadMusicRequest(Activity activity){
        musicItems.setValue(getMusicFromJson(getJsonFromAsset(activity, FILE_NAME)));
    }

    public MutableLiveData<List<MusicItem>> getMusicItems() {
        return musicItems;
    }
}