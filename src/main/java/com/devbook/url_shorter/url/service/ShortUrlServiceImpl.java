package com.devbook.url_shorter.url.service;

import com.devbook.url_shorter.url.model.ShortUrl;
import com.devbook.url_shorter.url.respository.ShortUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public String convertUrl(String url) {

        log.info("Url to convert: {}", url);

        ShortUrl shortUrl = ShortUrl.builder()
                .id("")
                .shortUrl("")
                .originalUrl(url)
                .createdAt(null)
                .expiresAt(null)
                .userId("")
                .clickCount(0)
                .lastAccessedAt(null)
                .isActive(true)
                .build();

        log.info("Object to save: {}", shortUrl);

        try {
            shortUrlRepository.save(shortUrl);

            log.info("Object saved");

            return url + ": shorter";

        } catch (Exception e) {
            log.error("Error trying to save object, detail: {}", e.getMessage());
            return "Error trying to save object";
        }
    }
}
