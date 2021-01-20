package com.lbd.model;

public class MusicInfoBO {
    private MusicDO music;
    private String musicFilePath;
    private String lyricsFilePath;

    public MusicDO getMusic() {
        return music;
    }

    public void setMusic(MusicDO music) {
        this.music = music;
    }

    public String getMusicFilePath() {
        return musicFilePath;
    }

    public void setMusicFilePath(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    public String getLyricsFilePath() {
        return lyricsFilePath;
    }

    public void setLyricsFilePath(String lyricsFilePath) {
        this.lyricsFilePath = lyricsFilePath;
    }
}
