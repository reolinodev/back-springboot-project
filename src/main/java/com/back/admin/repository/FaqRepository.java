package com.back.admin.repository;

import com.back.admin.domain.FaqEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository {

    List<FaqEntity> findAll(FaqEntity faqEntity);

    int countAll(FaqEntity faqEntity);

    int save(FaqEntity faqEntity);

    FaqEntity findByFaqId(String faqId);

    int update(FaqEntity faqEntity);
}
