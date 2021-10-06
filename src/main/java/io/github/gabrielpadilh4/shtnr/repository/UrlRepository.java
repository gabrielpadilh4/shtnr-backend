package io.github.gabrielpadilh4.shtnr.repository;

import io.github.gabrielpadilh4.shtnr.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlRepository extends MongoRepository<Url, String> {

    @Query("{shortLink:'?0'}")
    Url findByShotLink(String shortLink);
}
