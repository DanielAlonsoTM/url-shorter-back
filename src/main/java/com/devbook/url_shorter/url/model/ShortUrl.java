package com.devbook.url_shorter.url.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("ShortUrl")
public class ShortUrl {

    // The unique shortened identifier (e.g., abc123)
    @Id
    private String id;

    private String shortUrl;

    // The full original URL
    private String originalUrl;

    // When the short link was created
    private LocalDateTime createdAt;

    // When the short link should expire (if ever)
    private LocalDateTime expiresAt;

    // Who created the short URL (anonymous or registered user)
    private String userId; // optional user ID or null if anonymous

    // Number of times the link was visited
    private int clickCount;

    // Last time the link was visited
    private LocalDateTime lastAccessedAt;

    // Whether the short link is active (for deactivation, moderation, etc.)
    private boolean isActive;

    public boolean validateIdLength(String id) {
        return id.length() == 9;
    }
}
