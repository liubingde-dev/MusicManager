package com.lbd.service;

import com.lbd.model.MusicDO;
import com.lbd.model.MusicsQuery;

import java.util.List;

public interface MusicService {
     MusicsQuery getMusicsByName(String name);
     MusicsQuery getMusicByAuthor(String author);
}
