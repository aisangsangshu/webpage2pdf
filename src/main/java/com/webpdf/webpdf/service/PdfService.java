package com.webpdf.webpdf.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class PdfService {
    Logger logger = Logger.getLogger(PdfService.class.getName());
    public final String path = "E:\\webpage2pdf\\webpage2pdf\\";

    @Autowired
    private JavaMailSender javaMailSender;

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

    public boolean sendMail(String url,String email){
        String path =html2pdf(url);
        if (path==null||path.isEmpty()) {
            return false;
        }
        File file=new File(path);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Converted Pdf Page by WebPdf ");
            mimeMessageHelper.setText("Here is your convertd file ready to download ");
            Resource resource=new FileSystemResource(file);
            mimeMessageHelper.addAttachment(file.getName(), resource);
            javaMailSender.send(mimeMessage);
            file.deleteOnExit();
            logger.info("email sent : "+email);
            return true;
        } catch (MessagingException e) {
            logger.severe("faild to send email");
            e.printStackTrace();
            return false;

        }
    }
}
