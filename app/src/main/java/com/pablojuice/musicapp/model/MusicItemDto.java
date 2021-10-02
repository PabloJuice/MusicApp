package com.pablojuice.musicapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MusicItemDto implements Parcelable {
    private List<MusicItem> data = new ArrayList<>();

    public MusicItemDto() {
    }

    public MusicItemDto(List<MusicItem> data) {
        this.data.addAll(data);
    }

    protected MusicItemDto(Parcel in) {
    }

    public static final Creator<MusicItemDto> CREATOR = new Creator<MusicItemDto>() {
        @Override
        public MusicItemDto createFromParcel(Parcel in) {
            return new MusicItemDto(in);
        }

        @Override
        public MusicItemDto[] newArray(int size) {
            return new MusicItemDto[size];
        }
    };

    public void setData(List<MusicItem> data) {
        this.data = data;
    }

    public List<MusicItem> getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
