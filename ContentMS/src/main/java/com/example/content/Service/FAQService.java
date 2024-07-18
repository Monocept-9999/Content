package com.example.content.Service;

import com.example.content.Entities.FAQ;

public interface FAQService {

    FAQ createFAQ(FAQ faq);
    String deleteFAQ(Long faqId);
}
