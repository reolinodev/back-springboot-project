package com.back.admin.repository;

import com.back.admin.domain.CodeGrpEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGrpRepository {
    List<CodeGrpEntity> findAll(CodeGrpEntity codeGrpEntity);

    int countAll(CodeGrpEntity codeGrpEntity);

    int countByCodeGrpVal(CodeGrpEntity codeGrpEntity);

    int save(CodeGrpEntity codeGrpEntity);

    CodeGrpEntity findByCodeGrpId(String cdGrpId);

    int update(CodeGrpEntity codeGrpEntity);
}
