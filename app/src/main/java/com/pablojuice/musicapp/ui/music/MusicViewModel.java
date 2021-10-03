package com.pablojuice.musicapp.ui.music;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pablojuice.musicapp.model.MusicItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MusicViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isPlayerReady = new MutableLiveData<>(false);
    private MediaPlayer mediaPlayer;
    private List<MusicItem> items;

    public void setupMusicPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
    }

    public void prepareFromUrl(String url) {
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setOnPreparedListener(mediaPlayer -> isPlayerReady.postValue(true));
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void togglePlayer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    public void stopPlayer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (isPlayerReady.getValue()) {
            mediaPlayer.reset();
        }
        isPlayerReady.postValue(false);
    }

    public void releasePlayer() {
        CompletableFuture.runAsync(() -> {
            mediaPlayer.release();
            mediaPlayer = null;
        });
    }

    public List<MusicItem> getItems() {
        return items;
    }

    public void setItems(List<MusicItem> items) {
        this.items = items;
    }

    public MutableLiveData<Boolean> getIsPlayerReady() {
        return isPlayerReady;
    }
}
