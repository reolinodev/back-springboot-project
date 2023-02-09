package com.back.admin.repository;

import com.back.admin.domain.Code;
import com.back.admin.domain.CodeEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository {

    List<CodeEntity> findByCodeGrpId(String codeId);

    int save(Code code);

    int update(Code code);

    int deleteByCodeId(String codeId);
}
