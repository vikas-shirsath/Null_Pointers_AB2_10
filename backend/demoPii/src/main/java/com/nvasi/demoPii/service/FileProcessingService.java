package com.nvasi.demoPii.service;

import com.nvasi.demoPii.service.PiiDetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class FileProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessingService.class);

    private final PiiDetectionService piiDetectionService;
    private final OCRservice ocrService;

    @Autowired
    public FileProcessingService(PiiDetectionService piiDetectionService, OCRservice ocrService) {
        this.piiDetectionService = piiDetectionService;
        this.ocrService = ocrService;
    }

    public String processFile(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        String extractedText = "";

        if (contentType.equals("application/pdf") || contentType.equals("text/plain")) {
            // Use PiiDetectionService for PDFs and text files
            extractedText = piiDetectionService.extractText(file);
        } else if (contentType.equals("image/jpeg") || contentType.equals("image/png")) {
            // Use OCRService for images
            File tempFile = convertMultipartFileToFile(file);
            extractedText = ocrService.extractText(tempFile.getAbsolutePath());
            tempFile.delete(); // Delete temp file after processing
        } else {
            throw new UnsupportedOperationException("Unsupported file type: " + contentType);
        }


        return extractedText;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", null);
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    public Map<String, Object> detectPiiWithRegex(String text) {
        return PIIDetector.detectPII(text);
    }
}

