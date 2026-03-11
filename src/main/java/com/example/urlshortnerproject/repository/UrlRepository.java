package com.example.urlshortnerproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlshortnerproject.entity.UrlMapping;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {

    Optional<UrlMapping> findByShortCode(String shortCode);

}