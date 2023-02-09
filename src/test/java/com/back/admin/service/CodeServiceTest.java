package com.back.admin.service;

import com.back.admin.domain.Code;
import com.back.admin.domain.CodeEntity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void getCodeList() {
        //given
        String codeGrpId = "CDG0000002";
        //when
        List<CodeEntity> result =  codeService.getCodeList(codeGrpId);
        //then
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void  saveCode() {
        //given
        int result = 1;
        CodeEntity codeEntity = new CodeEntity();

        Code[] createdRows = new Code[0];
        Code[] updatedRows = new Code[0];
        Code[] deletedRows =new Code[2];

        String codeGrpId = "CDG0000001";
        String updatedId = "US00000002";

//        Code createCode1 = new Code();
//        createCode1.code_grp_id = "CDG0000001";
//        createCode1.code_nm = "예";
//        createCode1.code_val = "Y";
//        createCode1.memo ="aaa";
//        createCode1.ord ="1";
//
//        Code createCode2 = new Code();
//        createCode2.code_grp_id = "CDG0000001";
//        createCode2.code_nm = "아니오";
//        createCode2.code_val = "N";
//        createCode2.memo ="bbb";
//        createCode2.ord ="2";
//
//        createdRows[0] = createCode1;
//        createdRows[1] = createCode2;

//        Code updateCode1 = new Code();
//        updateCode1.code_id = "CD00000002";
//        updateCode1.code_nm = "예1";
//        updateCode1.memo ="";
//        updateCode1.ord ="3";
//        updateCode1.use_yn ="N";
//
//        updatedRows[0] = updateCode1;

        Code deleteCode1 = new Code();
        deleteCode1.code_id = "CD00000001";

        Code deleteCode2 = new Code();
        deleteCode2.code_id = "CD00000002";

        deletedRows[0] = deleteCode1;
        deletedRows[1] = deleteCode2;

        codeEntity.created_rows = createdRows;
        codeEntity.updated_rows = updatedRows;
        codeEntity.deleted_rows = deletedRows;
        codeEntity.updated_id = updatedId;
        codeEntity.code_grp_id = codeGrpId;

        //when
        int result2 = codeService.saveCode(codeEntity);

        //then
        Assertions.assertEquals(1, result);
    }


/*********************************************************/

//    @Test
//    public void getCodeItemList() {
//        //given
//        String codeGrpVal = "USE_YN";
//        //when
//        List<CodeEntity> result =  codeRepository.findByCdGrpVal(codeGrpVal);
//
//        //then
//        Assertions.assertEquals(2, result.size());
//
//    }
}