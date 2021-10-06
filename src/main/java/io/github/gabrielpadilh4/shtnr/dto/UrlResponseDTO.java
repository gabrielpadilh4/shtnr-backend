package io.github.gabrielpadilh4.shtnr.dto;

import io.github.gabrielpadilh4.shtnr.model.Url;

import java.time.LocalDateTime;

public class UrlResponseDTO {
    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;

    public UrlResponseDTO() {
    }

    public UrlResponseDTO(String originalUrl, String shortLink, LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.expirationDate = expirationDate;
    }

    public UrlResponseDTO(Url url){
        this.originalUrl = url.getOriginalUrl();
        this.expirationDate = url.getExpirationDate();
        this.shortLink = url.getShortLink();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponseDTO{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
