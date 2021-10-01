package com.pablojuice.musicapp.model;

import java.util.ArrayList;
import java.util.List;

public class MusicItemDto {
    private List<MusicItem> data = new ArrayList<>();

    public MusicItemDto(){}

    public MusicItemDto(List<MusicItem> data) {
        this.data.addAll(data);
    }

    public void setData(List<MusicItem> data) {
        this.data = data;
    }

    public List<MusicItem> getData() {
        return data;
    }
}
