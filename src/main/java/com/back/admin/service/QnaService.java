package com.back.admin.service;

import com.back.admin.domain.LoginEntity;
import com.back.admin.domain.QnaEntity;
import com.back.admin.repository.QnaRepository;
import com.back.support.CryptUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<QnaEntity> getQnaList(QnaEntity qnaEntity) {
        qnaEntity.setStart_idx(qnaEntity.getPage_per(), qnaEntity.getCurrent_page());
        return qnaRepository.findAll(qnaEntity);
    }

    public int getQnaCount(QnaEntity qnaEntity) {
        return qnaRepository.countAll(qnaEntity);
    }

    public int saveQna(QnaEntity qnaEntity) throws Exception {
        return qnaRepository.save(qnaEntity);
    }

    public QnaEntity getQnaData(String qnaId) {
        return qnaRepository.findByQnaId(qnaId);
    }

    public int updateQna(QnaEntity qnaEntity) throws Exception {

        if(!"".equals(qnaEntity.main_text) && qnaEntity.main_text != null){
            qnaEntity.response_yn ="Y";
            qnaEntity.response_id = qnaEntity.updated_id;
        }

        return qnaRepository.update(qnaEntity);
    }


    public int checkQnaPw(QnaEntity qnaEntity) throws Exception {
        qnaEntity.qna_pw = CryptUtils.encryptSha256(qnaEntity.qna_pw);
        return qnaRepository.countByQnaIdAndQnaPw(qnaEntity);
    }
}
