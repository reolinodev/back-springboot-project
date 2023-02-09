package com.back.admin.repository;

import com.back.admin.domain.QnaEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository {

    List<QnaEntity> findAll(QnaEntity qnaEntity);

    int countAll(QnaEntity qnaEntity);

    int save(QnaEntity qnaEntity);

    QnaEntity findByQnaId(String qnaId);

    int update(QnaEntity qnaEntity);

    int countByQnaIdAndQnaPw(QnaEntity qnaEntity);

}
