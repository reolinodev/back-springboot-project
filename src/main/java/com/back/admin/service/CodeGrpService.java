package com.back.admin.service;

import com.back.admin.domain.CodeGrpEntity;
import com.back.admin.repository.CodeGrpRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeGrpService {

    private final CodeGrpRepository codeGrpRepository;

    public List<CodeGrpEntity> getCodeGrpList(CodeGrpEntity codeGrpEntity) {
        codeGrpEntity.setStart_idx(codeGrpEntity.getPage_per(), codeGrpEntity.getCurrent_page());
        return codeGrpRepository.findAll(codeGrpEntity);
    }

    public int getCodeGrpCount(CodeGrpEntity codeGrpEntity) {
        return codeGrpRepository.countAll(codeGrpEntity);
    }

    public int checkCodeGrpVal(CodeGrpEntity codeGrpEntity) {
        return codeGrpRepository.countByCodeGrpVal(codeGrpEntity);
    }

    public int saveCodeGrp(CodeGrpEntity codeGrpEntity) {
        return codeGrpRepository.save(codeGrpEntity);
    }

    public CodeGrpEntity getCodeGrpData(String cdGrpId) {
        return codeGrpRepository.findByCodeGrpId(cdGrpId);
    }

    public int updateCodeGrp(CodeGrpEntity codeGrpEntity) {
        return codeGrpRepository.update(codeGrpEntity);
    }

}
