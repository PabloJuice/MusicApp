package com.pablojuice.musicapp.model;

import java.util.Objects;

public class MusicItem {
    private String title;
    private String author;
    private String imageSrc;
    private String musicSrc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getMusicSrc() {
        return musicSrc;
    }

    public void setMusicSrc(String musicSrc) {
        this.musicSrc = musicSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItem musicItem = (MusicItem) o;
        return Objects.equals(title, musicItem.title) && Objects.equals(author,
                                                                        musicItem.author) && Objects.equals(
                imageSrc,
                musicItem.imageSrc) && Objects.equals(musicSrc, musicItem.musicSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, imageSrc, musicSrc);
    }
}
