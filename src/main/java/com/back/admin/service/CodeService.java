package com.back.admin.service;

import com.back.admin.domain.Code;
import com.back.admin.domain.CodeEntity;
import com.back.admin.repository.CodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    public List<CodeEntity> getCodeList(String codeGrpId) {
        return codeRepository.findByCodeGrpId(codeGrpId);
    }

    public int saveCode(CodeEntity codeEntity) {
        Code[] createdRows = codeEntity.created_rows;
        Code[] updatedRows = codeEntity.updated_rows;
        Code[] deletedRows = codeEntity.deleted_rows;

        int result = 1;

        for (Code createCode : createdRows ) {
            createCode.created_id = codeEntity.updated_id;
            createCode.code_grp_id = codeEntity.code_grp_id;
            int result2 = codeRepository.save(createCode);
            if(result2 <= 0) result = 0;
        }

        for (Code updateCode : updatedRows ) {
            updateCode.updated_id = codeEntity.updated_id;
            int result2 = codeRepository.update(updateCode);
            if(result2 <= 0) result = 0;
        }

        for (Code deleteCode : deletedRows ) {
            String codeId = deleteCode.code_id;
            int result2 = codeRepository.deleteByCodeId(codeId);
            if(result2 <= 0) result = 0;
        }

        return result;
    }
}
