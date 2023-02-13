package com.back.admin.service;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.BoardEntity;
import com.back.admin.domain.Code;
import com.back.admin.domain.MenuEntity;
import com.back.admin.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Code> getItemCodeList(String codeGrpVal) {
        return itemRepository.findCodeByCodeGrpVal(codeGrpVal);
    }

    public List<AuthEntity> getItemAuthList(String useYn) {
        return itemRepository.findAuthByUseYn(useYn);
    }

    public List<AuthEntity> getItemMyAuthList(String userId) {
        return itemRepository.findAuthByUserId(userId);
    }

    public List<AuthEntity> getItemAuthRoleList(String authRole) {
        return itemRepository.findAuthByAuthRole(authRole);
    }

    public List<MenuEntity> getItemPrnMenuList() {
        int menuLv = 1;
        return itemRepository.findMenuByMenuLv(menuLv);
    }

    public List<BoardEntity> getItemUseYnBoardList(String useYn) {
        return itemRepository.findBoardByUseYn(useYn);
    }

}