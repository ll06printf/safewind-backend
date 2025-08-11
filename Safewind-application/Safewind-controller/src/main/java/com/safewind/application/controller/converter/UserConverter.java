package com.safewind.application.controller.converter;

import com.safewind.application.controller.vo.UserDeptVO;
import com.safewind.application.controller.vo.UserInfoVO;
import com.safewind.application.controller.vo.UserRoleVO;
import com.safewind.application.controller.vo.UserVO;
import com.safewind.domain.bo.UserBO;
import com.safewind.domain.bo.UserDeptBO;
import com.safewind.domain.bo.UserInfoBO;
import com.safewind.domain.bo.UserRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-09  15:52
 * @description: 用户实体转化
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE= Mappers.getMapper(UserConverter.class);

    // 用户实体转化
    UserVO userBOToVO(UserBO userBO);

    // 用户信息转化
    UserInfoVO userInfoBOToVO(UserInfoBO userInfoBO);

    // 用户角色转化
    UserRoleVO userRoleBOToVO(UserRoleBO userRoleBO);

    // 用户角色列表转化
    List<UserRoleVO> userRoleBOListToVOList(List<UserRoleBO> userRoleBOList);

    // 用户部门转化
    UserDeptVO userDeptBOToVO(UserDeptBO userDeptBO);
}
