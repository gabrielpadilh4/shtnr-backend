package io.github.gabrielpadilh4.shtnr.service.implementation;

import com.google.common.hash.Hashing;
import io.github.gabrielpadilh4.shtnr.dto.UrlDTO;
import io.github.gabrielpadilh4.shtnr.model.Url;
import io.github.gabrielpadilh4.shtnr.repository.UrlRepository;
import io.github.gabrielpadilh4.shtnr.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDTO urlDTO) {
        if (StringUtils.isNotEmpty(urlDTO.getUrl())) {

            if (!urlDTO.getUrl().contains("http://") && !urlDTO.getUrl().contains("https://")) {
                urlDTO.setUrl("https://".concat(urlDTO.getUrl()));
            }

            String encodedUrl = encodeUrl(urlDTO.getUrl());

            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(urlDTO.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            urlToPersist.setExpirationDate(getExpirationDate(urlDTO.getExpirationDate(), urlToPersist.getCreationDate()));

            return persistShortLink(urlToPersist);
        }

        return null;
    }

    @Override
    public Url persistShortLink(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

    @Override
    public Url getEncodedUrl(String url) {
        return urlRepository.findByShotLink(url);
    }

    private String encodeUrl(String url) {
        LocalDateTime time = LocalDateTime.now();

        return Hashing
                .murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if (StringUtils.isBlank(expirationDate)) {
            return creationDate.plusSeconds(60);
        }

        return LocalDateTime.parse(expirationDate);
    }
}
