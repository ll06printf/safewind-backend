package com.safewind.domain.converter;

import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;
import com.safewind.infra.basic.entity.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  21:52
 * @Description: 领域层菜单转化
 */
@Mapper
public interface MenuDomainConverter {
    MenuDomainConverter INSTANCE= Mappers.getMapper(MenuDomainConverter.class);

    /**
    * @Description: bo转化成数据库pojo
    * @param menuBO 参数
    * @return com.safewind.infra.basic.entity.SysMenu 返回值
    * @author Darven
    * @Date 2025/6/26
    */
    SysMenu menuBOToEntity(MenuBO menuBO);

    /**
     * @Description: bo转化成数据库pojo
     * @param sysMenu 参数
     * @return com.safewind.infra.basic.entity.SysMenu 返回值
     * @author Darven
     * @Date 2025/6/26
     */
    MenuBO entityToBO(SysMenu sysMenu);

    SysMenu menuListBOToEntity(MenuListBO menuListBO);

    List<MenuListBO> entityToListBO(List<SysMenu> sysMenuList);
}
