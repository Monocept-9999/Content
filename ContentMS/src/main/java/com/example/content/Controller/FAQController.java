package com.example.content.Controller;

import com.example.content.Entities.FAQ;
import com.example.content.Service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @PostMapping("/create-faq")
    public ResponseEntity<FAQ> createFAQ(@RequestBody FAQ faq){
        return new ResponseEntity<>(faqService.createFAQ(faq), HttpStatus.CREATED);
    }

    @GetMapping("/get-faq/{faqId}")
    public ResponseEntity<FAQ> getFAQById(@PathVariable("faqId") Long faqId){
        return new ResponseEntity<>(faqService.getFAQById(faqId), HttpStatus.OK);
    }

    @GetMapping("/get-faqs")
    public ResponseEntity<List<FAQ>> getAllFAQs(){
        return new ResponseEntity<>(faqService.getAllFAQs(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-faq/{faqId}")
    public ResponseEntity<String> deleteFAQ(@PathVariable("faqId") Long faqId){
        return new ResponseEntity<>(faqService.deleteFAQ(faqId), HttpStatus.OK);
    }
}
