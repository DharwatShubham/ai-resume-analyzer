package com.ai.resumeanalyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    public String analyzeResume(String resumeText, String jobDescription) {

        try {

            String prompt = """
                    You are an expert ATS Resume Reviewer.

                    Analyze the following resume against the job description.

                    Return ONLY in this format:

                    ATS Score:
                    Resume Summary:
                    Missing Skills:
                    Resume Improvements:
                    Best Matching Job Role:
                    Five Interview Questions:

                    JOB DESCRIPTION:
                    %s

                    RESUME:
                    %s
                    """.formatted(jobDescription, resumeText);

            String requestBody = """
                    {
                      "contents":[
                        {
                          "parts":[
                            {
                              "text": %s
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(toJson(prompt));

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            API_URL + apiKey,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response.getBody());

            return root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {

            e.printStackTrace();

            return "Gemini API Error: " + e.getMessage();
        }
    }

    private String toJson(String text) {

        return "\"" +
                text.replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                        .replace("\n", "\\n")
                + "\"";
    }

}