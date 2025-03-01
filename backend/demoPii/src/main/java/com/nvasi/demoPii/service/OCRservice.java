package com.nvasi.demoPii.service;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OCRservice {
    private static final Logger logger = LoggerFactory.getLogger(OCRservice.class);

    private final Tesseract tesseract;

    public OCRservice() {
        tesseract = new Tesseract();
        try {
            String tessDataPath = System.getenv("TESSDATA_PREFIX");
            if (tessDataPath == null) {
                tessDataPath = "src/main/resources/tessdata";
            }
            tesseract.setDatapath(tessDataPath);
            tesseract.setLanguage("eng");
            tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
            tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_AUTO);
        } catch (Exception e) {
            logger.error("Failed to initialize Tesseract", e);
            throw new RuntimeException("Failed to initialize Tesseract: " + e.getMessage(), e);
        }
    }

    public String extractText(String filePath) {
        try {
            File preprocessedFile = new File(filePath);
            logger.info("Preprocessed file path: {}", preprocessedFile.getAbsolutePath());
            logger.info("File exists: {}", preprocessedFile.exists());

            if (!preprocessedFile.exists()) {
                throw new RuntimeException("Preprocessed file does not exist: " + filePath);
            }

            File convertedFile = convertToPng(preprocessedFile);

            logger.info("Performing OCR on: {}", convertedFile.getAbsolutePath());
            String extractedText = tesseract.doOCR(convertedFile);
            logger.info("Raw OCR Output:\n{}", extractedText);

            String cleanedText = cleanOCRText(extractedText);
            logger.info("Cleaned OCR Output:\n{}", cleanedText);

            return cleanedText;
        } catch (Exception e) {
            logger.error("OCR failed", e);
            throw new RuntimeException("OCR failed: " + e.getMessage(), e);
        }
    }

    private File convertToPng(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        if (bufferedImage == null) {
            throw new IOException("Failed to read image file: " + file.getAbsolutePath());
        }

        File convertedFile = new File(file.getParent(), "converted.png");
        ImageIO.write(bufferedImage, "png", convertedFile);
        logger.info("Converted image to PNG: {}", convertedFile.getAbsolutePath());

        return convertedFile;
    }

    private String cleanOCRText(String text) {
        text = text.replace("ﬁ", "fi")
                .replace("’", "'")
                .replaceAll("[^a-zA-Z0-9\\s/:-]", ""); // Remove unwanted characters
        return text.replaceAll("\\s+", " ").trim();
    }
}