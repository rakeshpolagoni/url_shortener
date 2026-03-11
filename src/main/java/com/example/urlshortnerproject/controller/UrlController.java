package com.example.urlshortnerproject.controller;



import com.example.urlshortnerproject.entity.UrlMapping;
import com.example.urlshortnerproject.repository.UrlRepository;
import com.example.urlshortnerproject.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

@RestController
public class UrlController {

    @Autowired
    private UrlRepository repository;

    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {

        String code = service.generateShortCode();

        UrlMapping mapping = new UrlMapping(originalUrl, code);

        repository.save(mapping);

        return "http://localhost:8080/" + code;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {

        Optional<UrlMapping> mapping = repository.findByShortCode(code);

        if(mapping.isPresent()) {

            String originalUrl = mapping.get().getOriginalUrl();

            return ResponseEntity
                    .status(302)
                    .location(URI.create(originalUrl))
                    .build();
        }

        return ResponseEntity.notFound().build();
    }
}
