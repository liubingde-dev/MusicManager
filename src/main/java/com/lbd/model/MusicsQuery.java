package com.lbd.model;

import java.util.List;

public class MusicsQuery {
    private int resultCode;
    private int resultCount;
    private String desc;
    private List<MusicDO> musics;

    public MusicsQuery() {
    }

    public MusicsQuery(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<MusicDO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicDO> musics) {
        this.musics = musics;
    }
}
