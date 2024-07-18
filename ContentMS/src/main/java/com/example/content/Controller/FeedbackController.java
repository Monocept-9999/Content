package com.example.content.Controller;

import com.example.content.Entities.Feedback;
import com.example.content.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create-feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback){
        return new ResponseEntity<>(feedbackService.createFeedback(feedback), HttpStatus.CREATED);
    }

    @GetMapping("get-feedback/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("feedbackId") Long feedbackId){
        return new ResponseEntity<>(feedbackService.getFeedbackById(feedbackId), HttpStatus.OK);
    }

    @GetMapping("/get-feedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-feedback/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("feedbackId") Long feedbackId){
        return new ResponseEntity<>(feedbackService.deleteFeedback(feedbackId), HttpStatus.OK);
    }
}
