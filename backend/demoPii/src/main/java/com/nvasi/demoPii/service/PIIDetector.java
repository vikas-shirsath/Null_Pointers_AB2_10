package com.nvasi.demoPii.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

@Service
public class PIIDetector {
    public static final String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    public static final String AADHAAR_REGEX = "\\b\\d{4}[- ]?\\d{4}[- ]?\\d{4}(?!\\d)\\b";
    public static final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
    public static final String PHONE_REGEX = "\\b(?:\\+\\d{1,3}[- ]?)?\\d{10}\\b";
    public static final String NAME_REGEX = "\\b[A-Z][a-z]+(?: [A-Z][a-z]+)*\\b";
    public static final String CREDIT_CARD_REGEX = "\\b\\d{4}[- ]?\\d{4}[- ]?\\d{4}[- ]?\\d{4}\\b|\\b\\d{4}[- ]?\\d{6}[- ]?\\d{5}\\b";
    public static final String PASSPORT_REGEX = "\\b[A-Z]{1,2}\\d{6,9}\\b";
    public static final String DOB_REGEX = "\\b\\d{2}[/-]\\d{2}[/-]\\d{4}\\b|\\b\\d{4}[/-]\\d{2}[/-]\\d{2}\\b";

    public static Map<String, Object> detectPII(String text) {
        Map<String, Object> results = new HashMap<>();

        detectAndAdd(text, "pan", PAN_REGEX, results);
        detectAndAdd(text, "aadhaar", AADHAAR_REGEX, results);
        detectAndAdd(text, "email", EMAIL_REGEX, results);
        detectAndAdd(text, "phone", PHONE_REGEX, results);
        detectAndAdd(text, "name", NAME_REGEX, results);
        detectAndAdd(text, "credit_card", CREDIT_CARD_REGEX, results);
        detectAndAdd(text, "passport", PASSPORT_REGEX, results);
        detectAndAdd(text, "dob", DOB_REGEX, results);

        return results;
    }

    private static void detectAndAdd(String text, String label, String regex, Map<String, Object> results) {
        List<String> matches = matchRegex(text, regex);
        results.put(label, !matches.isEmpty());
        results.put(label + "_matches", matches); // List of matched values
    }

    private static List<String> matchRegex(String text, String regex) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matches.add(matcher.group().trim());
        }
        return matches;
    }
}
