package com.devbook.url_shorter.url.service;

import com.devbook.url_shorter.url.model.RequestConvertUrl;
import com.devbook.url_shorter.url.model.ResponseConsultUrl;
import com.devbook.url_shorter.url.model.ShortUrl;
import com.devbook.url_shorter.url.respository.ShortUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.devbook.url_shorter.url.utils.Constants.*;

@Slf4j
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public String convertUrl(RequestConvertUrl request) {

        log.info("Request received: {}", request);

        LocalDateTime currentLDT = LocalDateTime.now();
        LocalDateTime expiredLDT = currentLDT.plusMonths(MONTHS_TO_EXPIRE);

        ShortUrl shortUrl = ShortUrl.builder()
                .id(UUID.randomUUID().toString())
                .shortUrl(shortUrl())
                .originalUrl(request.getUrl())
                .createdAt(currentLDT)
                .expiresAt(expiredLDT)
                .userId("")
                .clickCount(0)
                .lastAccessedAt(null)
                .isActive(true)
                .build();

        log.info("Object to save: {}", shortUrl);

        try {
            shortUrlRepository.save(shortUrl);

            log.info("Object saved");

            return shortUrl.getShortUrl();

        } catch (Exception e) {
            log.error("Error trying to save object, detail: {}", e.getMessage());
            return "Error trying to save object";
        }
    }

    @Override
    public List<ResponseConsultUrl> getUrlsActives() {
        List<ResponseConsultUrl> responseConsultUrls = new ArrayList<>();

        shortUrlRepository.findAll().forEach(it -> responseConsultUrls.add(ResponseConsultUrl.builder()
                .uuid(it.getId())
                .originalUrl(it.getOriginalUrl())
                .shorterUrl(it.getShortUrl())
                .expiredDate(it.getExpiresAt())
                .build()));

        return responseConsultUrls;
    }

    private String shortUrl() {
        StringBuilder stringBuilder = new StringBuilder(LENGTH_URL);

        for (int i = 0; i < LENGTH_URL; i++) {
            stringBuilder.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }

        return URL_BASE + stringBuilder;
    }
}
