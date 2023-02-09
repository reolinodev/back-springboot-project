package com.back.admin.repository;

import com.back.admin.domain.MenuEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository {

    List<MenuEntity> findMenuTreeByAuthRole(String authRole);

    int save(MenuEntity menuEntity);

    void update(MenuEntity menuEntity);

    int updateByMenuId(MenuEntity menuEntity);

    MenuEntity findByMenuId(String menuId);

    int countByMenuLvAndPrnMenuId(MenuEntity menuEntity);

    int deleteByMenuId(String menuId);


    List<MenuEntity> findByMenuLvAndPrnMenuId(MenuEntity menuEntity);

    /********************************/


    MenuEntity findByMainUrl(MenuEntity menuEntity);

    List<MenuEntity> findByMenuLvAndAuthId(MenuEntity menuEntity);



}
