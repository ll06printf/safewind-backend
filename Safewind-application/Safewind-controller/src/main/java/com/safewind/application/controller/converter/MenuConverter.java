package com.safewind.application.controller.converter;


import com.safewind.application.controller.dto.MenuDTO;
import com.safewind.application.controller.dto.MenuQueryDTO;
import com.safewind.application.controller.vo.MenuVO;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  19:45
 * @Description: 菜单转化器
 */
@Mapper
public interface MenuConverter {

    MenuConverter INSTANCE= Mappers.getMapper(MenuConverter.class);

    MenuBO menuDTOToBO(MenuDTO menuDTO);
    @Mapping(source = "menuListBOList", target = "menuVOList")
    MenuVO menuBOToVO(MenuListBO menuListBO);

    MenuListBO menuQueryDTOToBO(MenuQueryDTO menuQueryDTO);

    PageResult<MenuVO> pageMenuBOListToMenuVOList(PageResult<MenuListBO> menuListBOList);
    @Mapping(source = "menuListBOList", target = "menuVOList")
    List<MenuVO> menuBOListToMenuVOList(List<MenuListBO> menuListBOList);
}
