package com.nvasi.demoPii.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PiiDetectionService {

    private static final Logger logger = LoggerFactory.getLogger(PiiDetectionService.class);

    public String extractText(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        String text = "";
        if (contentType.equals("application/pdf")) {
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                text = pdfTextStripper.getText(document);
            }
        } else if (contentType.equals("text/plain")) {
            text = new String(file.getBytes());
        }  else {
            throw new UnsupportedOperationException("Unsupported file type: " + contentType);
        }
        logger.info("Extracted text: {}", text); // Log the extracted text
        return text;
    }

    // Method to extract text from an image using Tesseract OCR
//    public String extractTextFromImage(MultipartFile imageFile) throws IOException {
//        try {
//            // Validate the image file
//            if (!isValidImage(imageFile)) {
//                throw new IOException("Invalid image file");
//            }
//
//            Tesseract tesseract = new Tesseract();
//            // Set the path to the tessdata directory
//            tesseract.setDatapath(System.getenv("TESSDATA_PREFIX"));
//            // Set the language (e.g., "eng" for English)
//            tesseract.setLanguage("eng");
//            // Convert the MultipartFile to a File
//            File tempFile = File.createTempFile("temp", imageFile.getOriginalFilename());
//            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//                fos.write(imageFile.getBytes());
//            }
//            // Perform OCR
//            String text = tesseract.doOCR(tempFile);
//            // Delete the temporary file
//            tempFile.delete();
//            return text;
//        } catch (TesseractException e) {
//            throw new IOException("Failed to extract text from image", e);
//        }
//    }

    private boolean isValidImage(MultipartFile imageFile) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));
            return image != null; // Return true if the image is valid
        } catch (IOException e) {
            return false; // Return false if the image is invalid
        }
    }

//    // Method to detect PII using regex
//    public Map<String, Object> detectPiiWithRegex(String text) {
//        Map<String, Object> results = new HashMap<>();
//
//        // Aadhaar detection
//        Matcher aadhaarMatcher = AADHAAR_PATTERN.matcher(text);
//        List<String> aadhaarMatches = new ArrayList<>();
//        while (aadhaarMatcher.find()) {
//            aadhaarMatches.add(aadhaarMatcher.group());
//        }
//        results.put("aadhaar", !aadhaarMatches.isEmpty());
//        results.put("aadhaar_matches", aadhaarMatches);
//
//        // PAN detection
//        Matcher panMatcher = PAN_PATTERN.matcher(text);
//        List<String> panMatches = new ArrayList<>();
//        while (panMatcher.find()) {
//            panMatches.add(panMatcher.group());
//        }
//        results.put("pan", !panMatches.isEmpty());
//        results.put("pan_matches", panMatches);
//
//        return results;
//    }
}
