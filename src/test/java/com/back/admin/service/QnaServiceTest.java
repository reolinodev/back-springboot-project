package com.back.admin.service;

import com.back.admin.domain.QnaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaServiceTest {

    @Autowired
    private QnaService qnaService;

    @Test
    public void getQnaList() {
        //given
        QnaEntity qnaEntity = new QnaEntity();
        qnaEntity.page_per = 10;
        qnaEntity.current_page = 1;
        qnaEntity.search_str = "";
        qnaEntity.use_yn = "Y";

        //when
        var result =  qnaService.getQnaList(qnaEntity);

        //then
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getQnaCount() {
        //given
        QnaEntity qnaEntity = new QnaEntity();
        qnaEntity.page_per = 10;
        qnaEntity.current_page = 1;
        qnaEntity.search_str = "";
        qnaEntity.use_yn = "Y";

        //when
        var result =  qnaService.getQnaCount(qnaEntity);

        //then
        Assertions.assertEquals(0, result);
    }

    @Test
    public void saveQna() throws Exception {
        //given
        QnaEntity qnaEntity = new QnaEntity();
        qnaEntity.board_id = "BD00000007";
        qnaEntity.qna_title = "테스트 QNA";
        qnaEntity.questions = "질문입니다.";
        qnaEntity.hidden_yn = "N";
        qnaEntity.qna_pw = "1111";
        qnaEntity.created_id = "US00000002";

        //when
        var result =  qnaService.saveQna(qnaEntity);

        //then
        Assertions.assertEquals(1, result);
    }
    
    @Test
    public void getQnaData() {
        //given
        String qnaId = "FQ00000001";
        //when
        var result =  qnaService.getQnaData(qnaId);
        //then
        Assertions.assertEquals("테스트 FAQ", result.qna_title);
    }

    @Test
    public void updateQna() throws Exception {
        //given
        QnaEntity qnaEntity = new QnaEntity();
        qnaEntity.qna_title = "테스트12312312";
        qnaEntity.main_text = "테스트본문12321321321231";
        qnaEntity.updated_id = "US00000002";
        qnaEntity.use_yn = "N";
        qnaEntity.qna_id = "FQ00000001";

        //when
        var result =  qnaService.updateQna(qnaEntity);

        //then
        Assertions.assertEquals(1, result);
    }

}