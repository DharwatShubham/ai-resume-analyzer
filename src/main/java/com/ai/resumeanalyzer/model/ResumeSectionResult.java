package com.ai.resumeanalyzer.model;

import java.util.List;

public class ResumeSectionResult {

    private List<String> foundSections;
    private List<String> missingSections;

    public List<String> getFoundSections() {
        return foundSections;
    }

    public void setFoundSections(List<String> foundSections) {
        this.foundSections = foundSections;
    }

    public List<String> getMissingSections() {
        return missingSections;
    }

    public void setMissingSections(List<String> missingSections) {
        this.missingSections = missingSections;
    }
}