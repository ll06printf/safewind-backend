package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.RoleDTO;
import com.safewind.application.controller.dto.RoleQueryDTO;
import com.safewind.application.controller.dto.RoleUserDTO;
import com.safewind.application.controller.dto.RoleUserQueryDTO;
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
}
