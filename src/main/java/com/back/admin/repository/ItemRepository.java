package com.back.admin.repository;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.BoardEntity;
import com.back.admin.domain.Code;
import com.back.admin.domain.MenuEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository {

    List<Code> findCodeByCodeGrpVal(String codeGrpVal);

    List<AuthEntity> findAuthByUseYn(String useYn);

    List<AuthEntity> findAuthByUserId(String userId);

    List<AuthEntity> findAuthByAuthRole(String authRole);

    List<MenuEntity> findMenuByMenuLv(int menuLv);

    List<BoardEntity> findBoardByUseYn(String useYn);

}
