package com.webpdf.webpdf.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

public class HtmlToPdfConverter {

    private static Logger logger = Logger.getLogger(HtmlToPdfConverter.class.getName());

    public static void main(String[] args) {
        System.out.println("started ..");
        convertUrl("https://jsoup.org/cookbook/input/load-document-from-url");
        System.out.println("ends ...");

    }

    public static void convertUrl(String url) {
        if (urlExist(url)) {
            String path = "/home/mannu/Documents/output/output.pdf";
            ProcessBuilder processBuilder = new ProcessBuilder("wkhtmltopdf", url, path);
            processBuilder.redirectErrorStream(true);
            try {
                Process process = processBuilder.start();
                process.waitFor();
                System.out.println("pdf created ");
            } catch (Exception e) {
                System.out.println("some error occured " + e.getLocalizedMessage());
            }
        }
    }

    public static boolean urlExist(String url) {
        Connection connection = Jsoup.connect(url).ignoreContentType(true);
        Response response;
        try {
            response = connection.execute();
            int responseCode = response.statusCode();
            return responseCode == 200;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error got");
            return false;
        }

    }
}
