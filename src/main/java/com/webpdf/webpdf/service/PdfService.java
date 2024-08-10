package com.webpdf.webpdf.service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class PdfService {
    Logger logger = Logger.getLogger(PdfService.class.getName());
    public final String path = "/home/mannu/Documents/webpdf/src/main/data/";

    public String html2pdf(String url) {
        String fileName = getNameFromUrl(url);
        if (fileName != null) {
            ProcessBuilder processBuilder = new ProcessBuilder("wkhtmltopdf", url, path.concat(fileName));
            logger.info("file name is : " + path.concat(fileName));
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();
                process.waitFor();
                File outputFile = new File(path.concat(fileName));
                if (outputFile.exists()) {
                    logger.info("file path " + outputFile.getAbsolutePath());
                    return outputFile.getAbsolutePath();
                }
            } catch (Exception e) {
                logger.info("file not found exception found ");
                System.out.println("some error occured " + e.getLocalizedMessage());
            }
        }
        logger.info("url not found");
        return null;
    }

    public String getNameFromUrl(String url) {
        Document document;
        String title;
        try {
            document = Jsoup.connect(url).get();
            title = document.title();
            logger.info("title is : "+title);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return title;
    }

}
