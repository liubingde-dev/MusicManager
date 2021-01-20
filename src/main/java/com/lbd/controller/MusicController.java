package com.lbd.controller;

import com.lbd.model.MusicsQuery;
import com.lbd.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/musics")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public MusicsQuery getMusicsByName(@RequestParam String name) {
        return musicService.getMusicsByName(name);
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public MusicsQuery getMuscisByAuthor(@RequestParam String author) {
        return musicService.getMusicByAuthor(author);
    }
}
