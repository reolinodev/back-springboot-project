package com.back.admin.repository;

import com.back.admin.domain.MenuAuth;
import com.back.admin.domain.MenuAuthEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuAuthRepository {

    List<MenuAuthEntity> findByMenuId(String menuId);

    int save(MenuAuth menuAuth);

    int deleteByMenuId(String menuId);
}
