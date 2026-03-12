package com.example.urlshortnerproject.service;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.urlshortnerproject.repository.*;

import java.io.InputStream;
import java.util.*;
import com.example.urlshortnerproject.entity.*;
@Service
public class ReportService {

    @Autowired
    private UrlRepository repository;

    public byte[] generateReport() throws Exception {

        List<UrlMapping> urls = repository.findAll();

        InputStream reportStream =
                getClass().getResourceAsStream("/reports/url_report.jrxml");

        JasperReport report =
                JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(urls);

        Map<String,Object> parameters = new HashMap<>();

        JasperPrint print =
                JasperFillManager.fillReport(report, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(print);
    }
}
