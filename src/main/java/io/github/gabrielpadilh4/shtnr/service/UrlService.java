package io.github.gabrielpadilh4.shtnr.service;

import io.github.gabrielpadilh4.shtnr.dto.UrlDTO;
import io.github.gabrielpadilh4.shtnr.model.Url;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    Url generateShortLink(UrlDTO urlDTO);

    Url persistShortLink(Url url);

    void deleteShortLink(Url url);

    Url getEncodedUrl(String url);
}
