package com.devbook.url_shorter.url.respository;

import com.devbook.url_shorter.url.model.ShortUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrl, String> {
}
