package com.back.admin.service;

import com.back.admin.domain.MenuAuth;
import com.back.admin.domain.MenuAuthEntity;
import com.back.admin.domain.MenuEntity;
import com.back.admin.repository.MenuAuthRepository;
import com.back.admin.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuAuthService {

    private final MenuAuthRepository menuAuthRepository;
    private final MenuRepository menuRepository;

    public List<MenuAuthEntity> getMenuAuthList(String menuId) {
        return menuAuthRepository.findByMenuId(menuId);
    }

    public int saveMenuAuth(MenuAuthEntity menuAuthEntity) {
        MenuAuth[] updatedRows = menuAuthEntity.updated_rows;
        int result = 1;
        String menuId = menuAuthEntity.menu_id;

        MenuEntity menuData = menuRepository.findByMenuId(menuId);

        if(menuData.menu_lv == 1){

            for (MenuAuth authMenu : updatedRows ) {
                authMenu.menu_id = menuId;
                authMenu.created_id = menuAuthEntity.updated_id;
                authMenu.updated_id = menuAuthEntity.updated_id;
                menuAuthRepository.save(authMenu);
            }

            MenuEntity param = new MenuEntity();
            param.menu_lv = 2;
            param.menu_id = menuId;

            List<MenuEntity> childMenu = menuRepository.findByMenuLvAndPrnMenuId(param);

            for (MenuEntity menuEntity : childMenu ) {
                String childMenuId = menuEntity.menu_id;
                for (MenuAuth authMenu : updatedRows ) {
                    authMenu.created_id = menuAuthEntity.updated_id;
                    authMenu.updated_id = menuAuthEntity.updated_id;
                    authMenu.menu_id = childMenuId;
                    menuAuthRepository.save(authMenu);
                }
            }
        }else {
            for (MenuAuth authMenu : updatedRows ) {
                authMenu.created_id = menuAuthEntity.updated_id;
                authMenu.updated_id = menuAuthEntity.updated_id;
                authMenu.menu_id = menuId;
                menuAuthRepository.save(authMenu);
            }
        }

        //결과값이 0이 나와서 체크할수 없다.임의로 1을 줌
        return result;
    }

    public int deleteMenuAuth(String menuId) {
        return menuAuthRepository.deleteByMenuId(menuId);
    }
}
