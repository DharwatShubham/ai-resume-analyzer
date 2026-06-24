package com.ai.resumeanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ai.resumeanalyzer.model.ATSResult;
import com.ai.resumeanalyzer.model.ResumeFeedback;
import com.ai.resumeanalyzer.model.ResumeResult;
import com.ai.resumeanalyzer.service.ATSAnalyzerService;
import com.ai.resumeanalyzer.service.GeminiService;
import com.ai.resumeanalyzer.service.ResumeAnalyzerService;
import com.ai.resumeanalyzer.service.ResumeFeedbackService;
import com.ai.resumeanalyzer.util.PdfReaderUtil;

@Controller
public class HomeController {

    @Autowired
    private ResumeAnalyzerService resumeAnalyzerService;

    @Autowired
    private ATSAnalyzerService atsAnalyzerService;

    @Autowired
    private ResumeFeedbackService resumeFeedbackService;

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("resume") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription,
            Model model) {

        try {

            // Extract text from uploaded PDF
            String resumeText = PdfReaderUtil.extractText(file);

            // Resume Analysis (Correct parameter order)
            ResumeResult result = resumeAnalyzerService.analyze(
                    file.getOriginalFilename(),
                    resumeText
            );

            // ATS Analysis
            ATSResult atsResult = atsAnalyzerService.analyze(
                    resumeText,
                    jobDescription
            );

            // Resume Feedback
            ResumeFeedback feedback = resumeFeedbackService.analyze(
                    resumeText
            );

            // Gemini AI Analysis
            String aiResponse = geminiService.analyzeResume(
                    resumeText,
                    jobDescription
            );

            // Send data to result.html
            model.addAttribute("result", result);
            model.addAttribute("atsResult", atsResult);
            model.addAttribute("feedback", feedback);
            model.addAttribute("jobDescription", jobDescription);
            model.addAttribute("aiResponse", aiResponse);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to analyze resume: " + e.getMessage());
            return "index";
        }

        return "result";
    }
}