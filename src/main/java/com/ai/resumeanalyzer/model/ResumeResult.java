package com.ai.resumeanalyzer.model;

import java.util.List;

public class ResumeResult {

    private String fileName;
    private String resumeText;
    private List<String> detectedSkills;
    private int score;
    private List<String> recommendedJobs;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public List<String> getDetectedSkills() {
        return detectedSkills;
    }

    public void setDetectedSkills(List<String> detectedSkills) {
        this.detectedSkills = detectedSkills;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getRecommendedJobs() {
        return recommendedJobs;
    }

    public void setRecommendedJobs(List<String> recommendedJobs) {
        this.recommendedJobs = recommendedJobs;
    }
}