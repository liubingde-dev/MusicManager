package com.lbd.service.impl;

import com.lbd.manager.MusicSearcher;
import com.lbd.model.MusicDO;
import com.lbd.model.MusicInfoBO;
import com.lbd.model.MusicsQuery;
import com.lbd.service.MusicService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicServiceImpl implements MusicService {

    @Override
    public MusicsQuery getMusicsByName(String name) {
        List<MusicInfoBO> result = MusicSearcher.getInstance().fuzzyMatchByName(name);
        return getMusicsQueryByBOList(result);
    }

    @Override
    public MusicsQuery getMusicByAuthor(String author) {
        List<MusicInfoBO> result = MusicSearcher.getInstance().fuzzySearchByAuthor(author);
        return getMusicsQueryByBOList(result);
    }

    private MusicsQuery getMusicsQueryByBOList(List<MusicInfoBO> musicInfoBOS) {
        if (!musicInfoBOS.isEmpty()) {
            MusicsQuery musicsQuery = new MusicsQuery();
            List<MusicDO> musicDOS = new ArrayList<>(musicInfoBOS.size());
            for (MusicInfoBO musicInfoBO : musicInfoBOS) {
                musicDOS.add(musicInfoBO.getMusic());
            }
            musicsQuery.setMusics(musicDOS);
            musicsQuery.setResultCount(musicDOS.size());
            return musicsQuery;
        }
        return new MusicsQuery(500);
    }
}
