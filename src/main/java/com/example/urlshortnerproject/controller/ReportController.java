package com.example.urlshortnerproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.urlshortnerproject.service.*;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/url-report")
    public ResponseEntity<byte[]> generateReport() throws Exception {

        byte[] pdf = reportService.generateReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=url_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
