package com.back.admin.service;

import com.back.admin.domain.CodeGrpEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGrpServiceTest {

    @Autowired
    private CodeGrpService codeGrpService;

    @Test
    public void getCodeGrpList() {
        //given
        CodeGrpEntity codeGrpEntity = new CodeGrpEntity();
        codeGrpEntity.use_yn = "Y";
        codeGrpEntity.code_grp_nm = "사용";
        //when
        List<CodeGrpEntity> result = codeGrpService.getCodeGrpList(codeGrpEntity);
        //then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void getCodeGrpCount() {
        //given
        CodeGrpEntity codeGrpEntity = new CodeGrpEntity();
        codeGrpEntity.use_yn = "Y";
        codeGrpEntity.code_grp_nm = "사용";
        //when
        int result = codeGrpService.getCodeGrpCount(codeGrpEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void checkCodeGrpVal() {
        //given
        CodeGrpEntity codeGrpEntity = new CodeGrpEntity();
        codeGrpEntity.code_grp_val = "USE_YN";
        //when
        int result = codeGrpService.checkCodeGrpVal(codeGrpEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void saveCodeGrp() {
        //given
        CodeGrpEntity codeGrpEntity = new CodeGrpEntity();
        codeGrpEntity.code_grp_nm = "사용여부";
        codeGrpEntity.code_grp_val = "USE_YN";
        codeGrpEntity.created_id = "US00000002";

        //when
        int result = codeGrpService.saveCodeGrp(codeGrpEntity);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getCodeGrpData() {
        //given
        String codeGrpId = "CDG0000001";
        //when
        CodeGrpEntity result = codeGrpService.getCodeGrpData(codeGrpId);
        //then
        Assertions.assertEquals("USE_YN", result.code_grp_val);
    }

    @Test
    public void updateCodeGrp() {
        //given
        CodeGrpEntity codeGrpEntity = new CodeGrpEntity();
        codeGrpEntity.code_grp_nm = "사용여부2";
        codeGrpEntity.use_yn = "Y";
        codeGrpEntity.code_grp_id = "CDG0000001";
        codeGrpEntity.updated_id = "US00000002";
        //when
        int result = codeGrpService.updateCodeGrp(codeGrpEntity);

        //then
        Assertions.assertEquals(1, result);
    }

}