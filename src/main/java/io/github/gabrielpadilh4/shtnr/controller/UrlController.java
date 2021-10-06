package io.github.gabrielpadilh4.shtnr.controller;

import io.github.gabrielpadilh4.shtnr.dto.UrlDTO;
import io.github.gabrielpadilh4.shtnr.dto.UrlResponseDTO;
import io.github.gabrielpadilh4.shtnr.model.Url;
import io.github.gabrielpadilh4.shtnr.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@CrossOrigin(origins = "*")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDTO urlDTO) {
        Url urlToReturn = urlService.generateShortLink(urlDTO);

        UrlResponseDTO urlResponseDTO = new UrlResponseDTO(urlToReturn);

        return new ResponseEntity<>(urlResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/shtnr/{shortLink}")
    public void redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(shortLink)) {
            // TODO - Throw error invalid url
        }

        Url urlToReturn = urlService.getEncodedUrl(shortLink);

        if (urlToReturn.getExpirationDate().isBefore(LocalDateTime.now())) {
            // TODO - Delete the short link and throw a error of link expired
        }

        response.sendRedirect(urlToReturn.getOriginalUrl());
    }
}
