package com.ai.resumeanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.resumeanalyzer.model.ResumeSectionResult;

@Service
public class ResumeSectionService {

    public ResumeSectionResult analyze(String resumeText) {

        ResumeSectionResult result = new ResumeSectionResult();

        String text = resumeText.toLowerCase();

        String[] sections = {
                "summary",
                "education",
                "skills",
                "projects",
                "experience",
                "certifications",
                "languages",
                "achievements"
        };

        List<String> found = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String section : sections) {

            if (text.contains(section.toLowerCase())) {
                found.add(section);
            } else {
                missing.add(section);
            }

        }

        result.setFoundSections(found);
        result.setMissingSections(missing);

        return result;
    }

}