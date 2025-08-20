package com.devbook.url_shorter.url.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseConsultUrl {
    private String uuid;
    private String originalUrl;
    private String shorterUrl;
    private LocalDateTime expiredDate;
}
