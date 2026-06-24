package com.ai.resumeanalyzer.model;

import java.util.List;

public class ResumeFeedback {

    private List<String> strengths;
    private List<String> weaknesses;

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }
}