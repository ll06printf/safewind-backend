package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.RoleConverter;
import com.safewind.application.controller.dto.RoleDTO;
import com.safewind.application.controller.dto.RoleQueryDTO;
import com.safewind.application.controller.dto.RoleUserDTO;
import com.safewind.application.controller.dto.RoleUserQueryDTO;
import com.safewind.application.controller.vo.RoleUserVO;
import com.safewind.application.controller.vo.RoleVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.RoleExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.*;
import com.safewind.domain.service.RoleDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: Darven
 * @createTime: 2025-07-01  10:39
 * @description: 角色管理
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private RoleDomainService roleDomainService;

    @ApiOperationLog(description = "查询角色列表")
    @PostMapping("/queryRole")
    public Result<PageResult<RoleVO>> queryRole(@RequestBody RoleQueryDTO roleQueryDTO){
        RoleBO roleBO = RoleConverter.INSTANCE.roleBOToListBO(roleQueryDTO);
        // 查询
        PageResult<RoleListBO> roleBOResult=roleDomainService.queryRole(roleBO);
        // 转换
        PageResult<RoleVO> roleVOResult = PageResult.<RoleVO>builder()
                .pageNum(roleBOResult.getPageNum())
                .pageSize(roleBOResult.getPageSize())
                .totalSize(roleBOResult.getTotalSize())
                .totalPages(roleBOResult.getTotalPages())
                .data(RoleConverter.INSTANCE.listBOToListVO(roleBOResult.getData()))
                .build();
        return Result.success(roleVOResult);
    }

    @ApiOperationLog(description = "添加角色列表")
    @PostMapping("/addRole")
    public Result<Boolean> addRole(@RequestBody RoleDTO roleDTO) {
        // 参数校验
        checkRole(roleDTO);
        // 添加
        Boolean b=roleDomainService.addRole(RoleConverter.INSTANCE.roleDTOToBO(roleDTO));
        return b?Result.success():Result.fail("添加失败");
    }


    @ApiOperationLog(description = "删除角色列表")
    @PostMapping("/deleteRole")
    public Result<RoleVO> deleteRole(@RequestBody RoleDTO roleDTO){
        if(roleDTO.getRoleId() == null){
            throw new BizException(RoleExceptionEnum.ID_NOT_NULL);
        }
        boolean b = roleDomainService.deleteRole(roleDTO.getRoleId());
        return b?Result.success():Result.fail("删除失败");
    }


    @ApiOperationLog(description = "修改角色列表")
    @PostMapping("/updateRole")
    public Result<Boolean> updateRole(@RequestBody RoleDTO roleDTO){
        // 参数校验
        checkRole(roleDTO);
        if(roleDTO.getRoleId() == null){
            throw new BizException(RoleExceptionEnum.ID_NOT_NULL);
        }
        // 实体转化
        RoleBO roleBO = RoleConverter.INSTANCE.roleDTOToBO(roleDTO);
        Boolean b=roleDomainService.updateRole(roleBO);
        return b?Result.success():Result.fail("修改失败");
    }

    @ApiOperationLog(description = "分配用户")
    @PostMapping("/distributionRole")
    public Result<Boolean> distributionRole(@RequestBody RoleUserDTO roleUserDTO) {
        // 校验角色id
        checkRoleUser(roleUserDTO);
        // 转化角色用户
        RoleUserBO roleUserBO = RoleConverter.INSTANCE.roleUserDTOToBO(roleUserDTO);
        // 返回结果
        return roleDomainService.distributionRole(roleUserBO)?Result.success() : Result.fail("分配用户失败");
    }

    @ApiOperationLog(description = "未分配的角色列表")
    @PostMapping("/queryUnDistributionRole")
    public Result<PageResult<RoleUserVO>> queryUnDistributionRole(@RequestBody RoleUserQueryDTO roleUserQueryDTO) {
        // 实体转化
        RoleUserQueryBO roleUserQueryBO = RoleConverter.INSTANCE.roleUserQueryDTOToBO(roleUserQueryDTO);
        // 查询
        PageResult<RoleUserListBO> roleListBO = roleDomainService.queryUnDistributionRole(roleUserQueryBO);
        // 实体转化
        PageResult<RoleUserVO> roleUserVOPageResult = RoleConverter.INSTANCE.pageBOToPageVO(roleListBO);
        // 返回
        return Result.success(roleUserVOPageResult);
    }

    @ApiOperationLog(description = "已分配的角色列表")
    @PostMapping("/queryDistributionRole")
    public Result<PageResult<RoleDTO>> queryDistributionRole(@RequestBody RoleUserQueryDTO roleUserQueryDTO) {
        return null;
    }

    @ApiOperationLog(description = "取消授权用户")
    @PostMapping("/cancelAuthorizeUser")
    public Result<Boolean> cancelAuthorizeUser(@RequestBody RoleDTO roleDTO) {
        return null;
    }

    @ApiOperationLog(description = "批量取消授权用户")
    @PostMapping("/batchCancelAuthorizeUser")
    public Result<Boolean> batchCancelAuthorizeUser(@RequestBody RoleDTO roleDTO) {
        return null;
    }

    /**
     * @param: roleDTO
     * @author Darven
     * @date 2025/7/1 13:52
     * @description: 统一参数校验
     */
    private void checkRole(RoleDTO roleDTO){
        if (roleDTO.getRoleName() == null || StringUtils.isBlank(roleDTO.getRoleName())) {
            throw new BizException(RoleExceptionEnum.NAME_NOT_NULL);
        }
        if (roleDTO.getRoleKey() == null || StringUtils.isBlank(roleDTO.getRoleKey())) {
            throw new BizException(RoleExceptionEnum.KEY_NOT_NULL);
        }
    }

    private void checkRoleUser(RoleUserDTO roleUserDTO){
        if (roleUserDTO.getRoleId() == null || roleUserDTO.getRoleId() <= 0) {
            throw new BizException(RoleExceptionEnum.ID_NOT_NULL);
        }
    }

}
