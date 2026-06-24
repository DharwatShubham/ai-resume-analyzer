package com.ai.resumeanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.resumeanalyzer.model.ATSResult;

@Service
public class ATSAnalyzerService {

    public ATSResult analyze(String resumeText, String jobDescription) {

        ATSResult result = new ATSResult();

        String resume = resumeText.toLowerCase();
        String jd = jobDescription.toLowerCase();

        String[] skills = {
                "java",
                "python",
                "sql",
                "spring boot",
                "git",
                "github",
                "aws",
                "docker",
                "kubernetes",
                "mongodb",
                "mysql",
                "opencv",
                "scikit-learn",
                "rest api",
                "microservices"
        };

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        int totalRequired = 0;

        for (String skill : skills) {

            if (jd.contains(skill)) {

                totalRequired++;

                if (resume.contains(skill)) {

                    matched.add(skill);

                } else {

                    missing.add(skill);
                    suggestions.add("Add experience with " + skill + " if applicable.");

                }
            }
        }

        int score = 0;

        if (totalRequired > 0) {
            score = (matched.size() * 100) / totalRequired;
        }

        result.setMatchScore(score);
        result.setMatchedSkills(matched);
        result.setMissingSkills(missing);
        result.setSuggestions(suggestions);

        return result;
    }
}