package com.example.content.ServiceImpl;

import com.example.content.Entities.FAQ;
import com.example.content.Repository.FAQRepository;
import com.example.content.Service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQServiceImpl implements FAQService {

    @Autowired
    private FAQRepository faqRepository;

    @Override
    public FAQ createFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }

    @Override
    public FAQ getFAQById(Long faqId) {
        return faqRepository.findById(faqId).get();
    }

    @Override
    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    @Override
    public String deleteFAQ(Long faqId) {
        return "FAQ deleted successfully with ID : "+ faqId;
    }
}
