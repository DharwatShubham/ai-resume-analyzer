package com.ai.resumeanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.resumeanalyzer.model.ResumeFeedback;

@Service
public class ResumeFeedbackService {

    public ResumeFeedback analyze(String resumeText) {

        ResumeFeedback feedback = new ResumeFeedback();

        List<String> strengths = new ArrayList<>();
        List<String> weaknesses = new ArrayList<>();

        String text = resumeText.toLowerCase();

        // -------- Strengths --------

        if (text.contains("python"))
            strengths.add("Strong Python programming skills");

        if (text.contains("java"))
            strengths.add("Java development experience");

        if (text.contains("sql"))
            strengths.add("Database and SQL knowledge");

        if (text.contains("aws"))
            strengths.add("AWS cloud experience");

        if (text.contains("git"))
            strengths.add("Version control using Git");

        if (text.contains("github"))
            strengths.add("GitHub project collaboration");

        if (text.contains("scikit"))
            strengths.add("Machine Learning experience");

        if (text.contains("opencv"))
            strengths.add("Computer Vision project experience");

        if (text.contains("project"))
            strengths.add("Hands-on project development");

        if (text.contains("certification"))
            strengths.add("Professional certifications included");

        // -------- Weaknesses --------

        if (!text.contains("docker"))
            weaknesses.add("Consider learning Docker.");

        if (!text.contains("kubernetes"))
            weaknesses.add("Learning Kubernetes will improve DevOps skills.");

        if (!text.contains("intern"))
            weaknesses.add("Add internship experience if available.");

        if (!text.contains("lead"))
            weaknesses.add("Highlight leadership or teamwork experience.");

        if (!text.contains("achievement"))
            weaknesses.add("Mention measurable achievements.");

        if (!text.contains("hackathon"))
            weaknesses.add("Hackathon participation can strengthen the resume.");

        feedback.setStrengths(strengths);
        feedback.setWeaknesses(weaknesses);

        return feedback;
    }
}