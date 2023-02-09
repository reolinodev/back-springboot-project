package com.back.admin.service;

import com.back.admin.domain.MenuEntity;
import com.back.admin.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuEntity> getMenuTreeList(String AuthRole) {
        return menuRepository.findMenuTreeByAuthRole(AuthRole);
    }

    public int saveMenu(MenuEntity menuEntity) {
        if("Y".equals(menuEntity.main_yn)){
            menuEntity.all_main_yn = "N";
            menuRepository.update(menuEntity);
        }

        return  menuRepository.save(menuEntity);
    }


    public int updateMenu(MenuEntity menuEntity) {
        if("Y".equals(menuEntity.main_yn)){
            menuEntity.all_main_yn = "N";
            menuRepository.update(menuEntity);
        }

        return  menuRepository.updateByMenuId(menuEntity);
    }

    public MenuEntity getMenuData(String menuId) {
        return menuRepository.findByMenuId(menuId);
    }


    public int deleteMenu(MenuEntity menuEntity) {
        String menuId = menuEntity.menu_id;

        if(menuEntity.menu_lv == 1){
            menuEntity.menu_lv = 2;
            int count = menuRepository.countByMenuLvAndPrnMenuId(menuEntity);
            if(count > 0) {
                return -1;
            }
        }

        return  menuRepository.deleteByMenuId(menuId);
    }


//
//    public List<MenuEntity> getMenuListLv(MenuEntity menuEntity) {
//        return menuRepository.findByMenuLvAndAuthId(menuEntity);
//    }
//
//    public MenuEntity getMainUrl(MenuEntity menuEntity) {
//        return menuRepository.findByMainUrl(menuEntity);
//    }
}
