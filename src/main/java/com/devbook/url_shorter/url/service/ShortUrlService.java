package com.devbook.url_shorter.url.service;

import com.devbook.url_shorter.url.model.RequestConvertUrl;
import com.devbook.url_shorter.url.model.ResponseConsultUrl;

import java.util.List;

public interface ShortUrlService {
    String convertUrl(RequestConvertUrl request);

    List<ResponseConsultUrl> getUrlsActives();
}
