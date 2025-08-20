package com.devbook.url_shorter.url.controller;

import com.devbook.url_shorter.url.model.RequestConvertUrl;
import com.devbook.url_shorter.url.model.ResponseConsultUrl;
import com.devbook.url_shorter.url.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "shorter")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("convert")
    public ResponseEntity<String> shortUrl(@RequestBody RequestConvertUrl request) {
        log.info("Init to convert url");

        return ResponseEntity.ok(this.shortUrlService.convertUrl(request));
    }

    @GetMapping("consult")
    public ResponseEntity<List<ResponseConsultUrl>> consultUrlActives() {
        log.info("Init consult url's actives");

        return ResponseEntity.ok(this.shortUrlService.getUrlsActives());
    }
}
