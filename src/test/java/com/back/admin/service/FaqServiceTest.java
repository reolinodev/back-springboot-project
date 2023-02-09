package com.back.admin.service;

import com.back.admin.domain.FaqEntity;
import com.back.admin.domain.FaqEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FaqServiceTest {

    @Autowired
    private FaqService faqService;

    @Test
    public void getFaqList() {
        //given
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.page_per = 10;
        faqEntity.current_page = 1;
        faqEntity.search_str = "test";
        faqEntity.use_yn = "Y";

        //when
        var result =  faqService.getFaqList(faqEntity);

        //then
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getFaqCount() {
        //given
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.page_per = 10;
        faqEntity.current_page = 1;
        faqEntity.search_str = "test";
        faqEntity.use_yn = "Y";

        //when
        var result =  faqService.getFaqCount(faqEntity);

        //then
        Assertions.assertEquals(0, result);
    }

    @Test
    public void saveFaq() {
        //given
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.board_id = "BD00000006";
        faqEntity.faq_title = "테스트 FAQ";
        faqEntity.main_text = "테스트 FAQ 본문 입니다.";
        faqEntity.created_id = "US00000002";

        //when
        var result =  faqService.saveFaq(faqEntity);

        //then
        Assertions.assertEquals(1, result);
    }
    
    @Test
    public void getFaqData() {
        //given
        String faqId = "FQ00000001";
        //when
        var result =  faqService.getFaqData(faqId);
        //then
        Assertions.assertEquals("테스트 FAQ", result.faq_title);
    }

    @Test
    public void updateFaq() {
        //given
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.faq_title = "테스트12312312";
        faqEntity.main_text = "테스트본문12321321321231";
        faqEntity.updated_id = "US00000002";
        faqEntity.use_yn = "N";
        faqEntity.faq_id = "FQ00000001";

        //when
        var result =  faqService.updateFaq(faqEntity);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    public void updateFaqViewCnt() {
        //given
        String faqId ="FQ00000001";

        //when
        var result =  faqService.updateFaqViewCnt(faqId);

        //then
        Assertions.assertEquals(1, result);
    }

}