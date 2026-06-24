package com.ai.resumeanalyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.resumeanalyzer.model.ResumeResult;

@Service
public class ResumeAnalyzerService {

    public ResumeResult analyze(String fileName, String resumeText) {

        ResumeResult result = new ResumeResult();

        result.setFileName(fileName);
        result.setResumeText(resumeText);

        String lower = resumeText.toLowerCase();

        List<String> skills = new ArrayList<>();

        // ==========================
        // Programming Languages
        // ==========================

        if (lower.contains("java")) skills.add("Java");
        if (lower.contains("python")) skills.add("Python");
        if (lower.contains("c++")) skills.add("C++");
        if (lower.contains("c#")) skills.add("C#");
        if (lower.contains("javascript")) skills.add("JavaScript");
        if (lower.contains("sql")) skills.add("SQL");

        // ==========================
        // Frameworks
        // ==========================

        if (lower.contains("spring")) skills.add("Spring Boot");
        if (lower.contains("hibernate")) skills.add("Hibernate");
        if (lower.contains("react")) skills.add("React");
        if (lower.contains("angular")) skills.add("Angular");
        if (lower.contains("node")) skills.add("Node.js");

        // ==========================
        // AI / ML
        // ==========================

        if (lower.contains("machine learning")) skills.add("Machine Learning");
        if (lower.contains("deep learning")) skills.add("Deep Learning");
        if (lower.contains("tensorflow")) skills.add("TensorFlow");
        if (lower.contains("pytorch")) skills.add("PyTorch");
        if (lower.contains("scikit")) skills.add("Scikit-learn");
        if (lower.contains("opencv")) skills.add("OpenCV");
        if (lower.contains("pandas")) skills.add("Pandas");
        if (lower.contains("numpy")) skills.add("NumPy");

        // ==========================
        // Databases
        // ==========================

        if (lower.contains("mysql")) skills.add("MySQL");
        if (lower.contains("mongodb")) skills.add("MongoDB");

        // ==========================
        // Cloud & Tools
        // ==========================

        if (lower.contains("aws")) skills.add("AWS");
        if (lower.contains("git")) skills.add("Git");
        if (lower.contains("github")) skills.add("GitHub");
        if (lower.contains("docker")) skills.add("Docker");
        if (lower.contains("kubernetes")) skills.add("Kubernetes");
        if (lower.contains("maven")) skills.add("Maven");

        result.setDetectedSkills(skills);

        // ===================================================
        // Realistic Resume Score
        // ===================================================

        int score = 0;

        // Skills (30 Marks)
        score += Math.min(skills.size() * 2, 30);

        // Projects (15 Marks)
        int projectCount = 0;

        projectCount += countOccurrences(lower, "project");
        projectCount += countOccurrences(lower, "developed");
        projectCount += countOccurrences(lower, "built");
        projectCount += countOccurrences(lower, "implemented");

        score += Math.min(projectCount * 3, 15);

        // Experience (15 Marks)
        if (lower.contains("internship"))
            score += 8;

        if (lower.contains("experience"))
            score += 7;

        // Education (10 Marks)
        if (lower.contains("b.tech")
                || lower.contains("bachelor")
                || lower.contains("degree")) {
            score += 10;
        }

        // Certifications (10 Marks)
        int certCount = 0;

        certCount += countOccurrences(lower, "certification");
        certCount += countOccurrences(lower, "certificate");
        certCount += countOccurrences(lower, "certified");

        score += Math.min(certCount * 5, 10);

        // GitHub / Portfolio (5 Marks)
        if (lower.contains("github"))
            score += 3;

        if (lower.contains("portfolio"))
            score += 2;

        // Cloud & DevOps (5 Marks)
        if (lower.contains("aws"))
            score += 2;

        if (lower.contains("docker"))
            score += 2;

        if (lower.contains("kubernetes"))
            score += 1;

        // Resume Length (10 Marks)
        int wordCount = resumeText.split("\\s+").length;

        if (wordCount >= 350 && wordCount <= 900)
            score += 10;
        else if (wordCount >= 250)
            score += 6;
        else
            score += 2;

        // Final Score
        score = Math.min(score, 100);

        result.setScore(score);

        // ===================================================
        // Recommended Jobs
        // ===================================================

        List<String> jobs = new ArrayList<>();

        if (skills.contains("Java") && skills.contains("Spring Boot"))
            jobs.add("Java Spring Boot Developer");

        if (skills.contains("Python") && skills.contains("Scikit-learn"))
            jobs.add("Machine Learning Engineer");

        if (skills.contains("OpenCV"))
            jobs.add("Computer Vision Engineer");

        if (skills.contains("Python"))
            jobs.add("Python Developer");

        if (skills.contains("SQL"))
            jobs.add("Data Analyst");

        if (skills.contains("AWS"))
            jobs.add("Cloud Engineer");

        if (skills.contains("Docker"))
            jobs.add("DevOps Engineer");

        if (skills.contains("React"))
            jobs.add("Frontend Developer");

        if (skills.contains("Node.js"))
            jobs.add("Backend Developer");

        if (jobs.isEmpty())
            jobs.add("Software Engineer");

        result.setRecommendedJobs(jobs);

        return result;
    }

    // ===================================================
    // Helper Method
    // ===================================================

    private int countOccurrences(String text, String keyword) {

        int count = 0;
        int index = 0;

        while ((index = text.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }

        return count;
    }
}