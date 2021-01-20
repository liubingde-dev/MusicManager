package com.lbd.manager;

import com.lbd.model.MusicDO;
import com.lbd.model.MusicInfoBO;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicSearcher {
    private static Map<String, MusicInfoBO> nameSearchMap;
    private static Map<String, MusicInfoBO> authorSearchMap;
    private static volatile MusicSearcher instance;
    private static List<String> nameList;
    private static List<String> authorList;
    private static final int INIT_SIZE = 50000;

    public static synchronized MusicSearcher getInstance() {
        if (instance != null) {
            return instance;
        }
        nameSearchMap = new HashMap<>(INIT_SIZE);
        authorSearchMap = new HashMap<>(INIT_SIZE);
        nameList = new ArrayList<>(INIT_SIZE);
        authorList = new ArrayList<>(INIT_SIZE);
        List<MusicInfoBO> musicInfoBOS = MusicInfoLoader.getInstance().getMusicInfoList();
        for (MusicInfoBO musicInfo : musicInfoBOS) {
            MusicDO musicDO = musicInfo.getMusic();
            nameSearchMap.put(musicDO.getName(), musicInfo);
            authorSearchMap.put(musicDO.getAuthor(), musicInfo);
            nameList.add(musicDO.getName());
            authorList.add(musicDO.getAuthor());
        }
        instance = new MusicSearcher();
        return instance;
    }

    public MusicInfoBO getMusicByName(String name) {
        return nameSearchMap.get(name);
    }

    public MusicInfoBO getMusicByAuthor(String author) {
        return authorSearchMap.get(author);
    }

    public List<MusicInfoBO> fuzzyMatchByName(String name) {
        List<ExtractedResult> extractedResults = FuzzySearch.extractSorted(name, nameList, 50);
        List<MusicInfoBO> result = new ArrayList<>(extractedResults.size());
        for (ExtractedResult extractedResult : extractedResults) {
            MusicInfoBO musicInfoBO = getMusicByName(extractedResult.getString());
            result.add(musicInfoBO);
        }
        return result;
    }
    public List<MusicInfoBO> fuzzySearchByAuthor(String author){
        List<ExtractedResult> extractedResults = FuzzySearch.extractSorted(author, authorList, 50);
        List<MusicInfoBO> result = new ArrayList<>(extractedResults.size());
        for (ExtractedResult extractedResult : extractedResults) {
            MusicInfoBO musicInfoBO = getMusicByAuthor(extractedResult.getString());
            result.add(musicInfoBO);
        }
        return result;
    }
}
