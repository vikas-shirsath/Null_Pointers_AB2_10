package com.nvasi.demoPii.controller;


import com.nvasi.demoPii.service.FileProcessingService;
import com.nvasi.demoPii.service.PiiDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api")
public class PiiController {

    @Autowired
    private PiiDetectionService piiDetectionService;

    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping(value = "/detect", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> detectPii(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> response = new HashMap<>();
        try {
            String extractedText = fileProcessingService.processFile(file);
            Map<String, Object> piiData = fileProcessingService.detectPiiWithRegex(extractedText);

            response.put("status", "success");
            response.put("extractedText", extractedText);
            response.put("piiData", piiData);

            return piiData;
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "Failed to process file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response).getBody();
        } catch (UnsupportedOperationException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response).getBody();
        }
    }


}
