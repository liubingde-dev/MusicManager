package com.lbd.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbd.model.MusicDO;
import com.lbd.model.MusicInfoBO;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicInfoLoader {
    private static final String PATH_BASE = "D:\\music";
    private static final String JSON_KEY_NAME = "name";
    public static final String JSON_KEY_AUTHOR = "author";
    private static final String JSON_KEY_NUM = "num";
    private static final String SUFFIX_INFO = ".txt";
    private static final String SUFFIX_MUSIC_FILE = ".mp3";
    private static final String PATH_MUSIC_INFO = "info";
    private static final String PATH_MUSIC_LYRICS = "lyrics";
    private static final String PATH_MUSIC_FILE = "download";
    private static final int MUISIC_STEP = 1000;
    private static final List<MusicInfoBO> musicInfoList = new ArrayList<>(50000);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static MusicInfoLoader instance;

    public synchronized static MusicInfoLoader getInstance() {
        if (instance != null) {
            return instance;
        }
        File infoPath = new File(getInfoPath());
        File[] fs = infoPath.listFiles();
        try {
            for (File file : fs) {
                if (file.isFile()) {
                    String content=FileUtils.readFileToString(file,"utf-8");
                    JsonNode infoList = objectMapper.readTree(content);
                    Iterator<JsonNode> jsonNodeIterator = infoList.elements();
                    while (jsonNodeIterator.hasNext()) {
                        JsonNode info = jsonNodeIterator.next();
                        String name = info.path(JSON_KEY_NAME).toString();
                        String author = info.path(JSON_KEY_AUTHOR).toString();
                        int id = info.path(JSON_KEY_NUM).asInt();
                        MusicDO musicDO = new MusicDO();
                        musicDO.setName(name);
                        musicDO.setAuthor(author);
                        musicDO.setId(id);
                        MusicInfoBO musicInfoBO = new MusicInfoBO();
                        musicInfoBO.setMusic(musicDO);
                        musicInfoBO.setMusicFilePath(getMusicFilePath(id));
                        musicInfoBO.setLyricsFilePath(getLyricsPath(id));
                        musicInfoList.add(musicInfoBO);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = new MusicInfoLoader();
        return instance;
    }

    public List<MusicInfoBO> getMusicInfoList() {
        return musicInfoList;
    }

    private MusicInfoLoader() {

    }

    private static String getLyricsPath(int num) {
        return getLyricsPath() + File.separator + num / MUISIC_STEP + File.separator + num + SUFFIX_INFO;
    }

    private static String getMusicFilePath(int num) {
        return getMusicFilePath() + File.separator + num / MUISIC_STEP + File.separator + num + SUFFIX_MUSIC_FILE;
    }

    private static String getInfoPath() {
        return PATH_BASE + File.separator + PATH_MUSIC_INFO;
    }

    private static String getLyricsPath() {
        return PATH_BASE + File.separator + PATH_MUSIC_LYRICS;
    }

    private static String getMusicFilePath() {
        return PATH_BASE + File.separator + PATH_MUSIC_FILE;
    }
}
