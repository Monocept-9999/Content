package com.example.content.Service;

import com.example.content.Entities.FAQ;

import java.util.List;

public interface FAQService {
    FAQ createFAQ(FAQ faq);
    FAQ getFAQById(Long faqId);
    List<FAQ> getAllFAQs();
    String deleteFAQ(Long faqId);
}
