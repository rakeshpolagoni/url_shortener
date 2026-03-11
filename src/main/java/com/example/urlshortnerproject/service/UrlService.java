package com.example.urlshortnerproject.service;



import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class UrlService {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int LENGTH = 6;

    public String generateShortCode() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {

            int index = random.nextInt(CHARACTERS.length());

            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}