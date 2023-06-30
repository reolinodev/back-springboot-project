package com.back.admin.service;

import com.back.admin.domain.FaqEntity;
import com.back.admin.repository.FaqRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public List<FaqEntity> getFaqList(FaqEntity faqEntity) {
        faqEntity.setStart_idx(faqEntity.getPage_per(), faqEntity.getCurrent_page());
        return faqRepository.findAll(faqEntity);
    }

    public int getFaqCount(FaqEntity faqEntity) {
        return faqRepository.countAll(faqEntity);
    }

    public int saveFaq(FaqEntity faqEntity) {
        return faqRepository.save(faqEntity);
    }

    public FaqEntity getFaqData(String faqId) {
        return faqRepository.findByFaqId(faqId);
    }

    public int updateFaq(FaqEntity faqEntity) {
        return faqRepository.update(faqEntity);
    }

    public int updateFaqViewCnt(String faqId) {
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.faq_id = faqId;
        faqEntity.view = "Y";
        return faqRepository.update(faqEntity);
    }
}
