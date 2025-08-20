package com.devbook.url_shorter.url.controller;

import com.devbook.url_shorter.url.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "shorter")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("convert")
    public ResponseEntity<String> shortUrl(@RequestBody String url) {
        log.info("Init to convert url");

        return ResponseEntity.ok(this.shortUrlService.convertUrl(url));
    }
}
