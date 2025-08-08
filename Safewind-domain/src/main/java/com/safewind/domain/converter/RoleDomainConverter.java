package com.safewind.domain.converter;

import com.safewind.domain.bo.*;
import com.safewind.infra.basic.entity.RoleUser;
import com.safewind.infra.basic.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-07-01  11:17
 * @description: 角色域转换器
 */
@Mapper
public interface RoleDomainConverter {
    RoleDomainConverter INSTANCE= Mappers.getMapper(RoleDomainConverter.class);

    SysRole roleBOToEntity(RoleBO roleBO);

    List<RoleListBO> entityToListBO(List<SysRole> sysRoleList);

    RoleUser roleUserQueryBOToEntity(RoleUserQueryBO roleUserQueryBO);

    List<RoleUserListBO> roleUserListToListBO(List<RoleUser> roleUserList);
}
