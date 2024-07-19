package com.example.content.Service;

import com.example.content.Entities.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(Long feedbackId);
    List<Feedback> getAllFeedbacks();
    String deleteFeedback(Long feedbackId);
}
