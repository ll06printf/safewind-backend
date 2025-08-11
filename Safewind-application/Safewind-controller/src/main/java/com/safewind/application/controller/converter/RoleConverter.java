package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.*;
import com.safewind.application.controller.vo.RoleMenuVO;
import com.safewind.application.controller.vo.RoleUserVO;
import com.safewind.application.controller.vo.RoleVO;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-07-01  10:49
 * @description: 角色实体转化器
 */
@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE= Mappers.getMapper(RoleConverter.class);

    RoleBO roleDTOToBO(RoleDTO roleDTO);

    List<RoleVO> listBOToListVO(List<RoleListBO> roleBOList);

    RoleBO roleBOToListBO(RoleQueryDTO roleQueryDTO);

    RoleUserBO roleUserDTOToBO(RoleUserDTO roleUserDTO);

    RoleUserQueryBO roleUserQueryDTOToBO(RoleUserQueryDTO roleUserDTO);

    PageResult<RoleUserVO> pageBOToPageVO(PageResult<RoleUserListBO> roleUserListBO);

    /**
     * 角色菜单DTO转BO
     */
    RoleMenuBO roleMenuDTOToBO(RoleMenuDTO roleMenuDTO);

    /**
     * 角色菜单BO转VO
     */
    RoleMenuVO roleMenuBOToVO(RoleMenuListBO roleMenuListBO);

    /**
     * 角色菜单BO列表转VO列表
     */
    List<RoleMenuVO> roleMenuBOListToVOList(List<RoleMenuListBO> roleMenuListBOList);
}
