package com.webpdf.webpdf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.webpdf.webpdf.service.PdfService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    private final Logger logger = Logger.getLogger(HomeController.class.getName());

    @Autowired
    private PdfService pdfService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public ModelAndView home() {
        logger.info("homepage called");
        logger.info("user IP Address : " + request.getRemoteAddr());
        return new ModelAndView("index.html", HttpStatus.OK);
    }

    @GetMapping("/api/convert")
    public ResponseEntity<Resource> convertWebpage(@RequestParam("url") String url) {
        logger.info("converter started with url : " + url + " user IP Address : " + request.getRemoteAddr());
        String filePath = pdfService.html2pdf(url);
        if (filePath == null || filePath.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        File outputFile = new File(filePath);
        if (!outputFile.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Resource resource=new FileSystemResource(outputFile);
            // Prepare resource and headers
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + outputFile.getName() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return ResponseEntity.ok()
            .headers(headers)
            .contentLength(resource.contentLength())
            .body(resource);

        } catch (IOException e) {
            logger.warning("Error reading or deleting file : " + e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/convert/{url}/{email}")
    public ResponseEntity<String> convertAndSendEmail(@PathVariable("url") String url,
            @PathVariable("email") String email) {
        System.out.println("email called");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
