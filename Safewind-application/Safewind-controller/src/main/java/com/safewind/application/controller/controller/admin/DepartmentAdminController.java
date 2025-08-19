package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.DepartmentConverter;
import com.safewind.application.controller.dto.DepartmentDTO;
import com.safewind.application.controller.dto.DepartmentQueryDTO;
import com.safewind.application.controller.vo.DepartmentVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.DepartmentExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.bo.DepartmentQueryBO;
import com.safewind.domain.service.DepartmentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:28
 * @description: 部门管理
 */
@RestController
@RequestMapping("/admin-department")
public class DepartmentAdminController {

    @Autowired
    private DepartmentDomainService departmentDomainService;

    @ApiOperationLog(description = "部门列表-分页查询")
    @PostMapping("/getDepartment")
    public Result<PageResult<DepartmentVO>> getDepartment(@RequestBody DepartmentQueryDTO queryDTO) {
        // 将 DTO 转换为 BO 对象，用于传递给领域服务
        DepartmentQueryBO queryBO = DepartmentConverter.INSTANCE.queryDTOToQueryBO(queryDTO);

        // 调用领域服务进行分页查询，返回分页结果
        PageResult<DepartmentBO> pageResult = departmentDomainService.queryDepartmentPage(queryBO);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<DepartmentVO> voList = DepartmentConverter.INSTANCE.boListToVOList(pageResult.getData());

        // 构造分页结果的 VO 对象并返回
        PageResult<DepartmentVO> result = PageResult.<DepartmentVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    @ApiOperationLog(description = "添加部门")
    @PostMapping("/addDepartment")
    public Result<DepartmentVO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // 校验传入的部门数据是否为空
        if (Objects.isNull(departmentDTO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_IS_NULL);
        }

        // 将 DTO 转换为 BO 对象
        DepartmentBO departmentBO = DepartmentConverter.INSTANCE.dtoToBO(departmentDTO);

        // 调用领域服务保存部门数据
        DepartmentBO savedDepartment = departmentDomainService.addDepartment(departmentBO);

        // 将保存后的 BO 转换为 VO 并返回
        DepartmentVO departmentVO = DepartmentConverter.INSTANCE.boToVO(savedDepartment);
        return Result.success(departmentVO);
    }

    @ApiOperationLog(description = "修改部门")
    @PostMapping("/updateDepartment")
    public Result<DepartmentVO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // 校验传入的部门数据是否为空
        if (Objects.isNull(departmentDTO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_IS_NULL);
        }
        // 校验部门ID是否为空
        if (Objects.isNull(departmentDTO.getId())) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        // 将 DTO 转换为 BO 对象
        DepartmentBO departmentBO = DepartmentConverter.INSTANCE.dtoToBO(departmentDTO);

        // 调用领域服务更新部门数据
        DepartmentBO updatedDepartment = departmentDomainService.updateDepartment(departmentBO);

        // 将更新后的 BO 转换为 VO 并返回
        DepartmentVO departmentVO = DepartmentConverter.INSTANCE.boToVO(updatedDepartment);
        return Result.success(departmentVO);
    }

    @ApiOperationLog(description = "删除部门")
    @PostMapping("/deleteDepartment")
    public Result<Boolean> deleteDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // 校验传入的部门数据是否为空
        if (Objects.isNull(departmentDTO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_IS_NULL);
        }

        // 获取部门 ID 并校验是否为空
        Long id = departmentDTO.getId();
        if (Objects.isNull(id)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        // 调用领域服务删除部门
        Boolean result = departmentDomainService.deleteDepartment(id);
        return Result.success(result);
    }

    @ApiOperationLog(description = "根据ID获取部门详情")
    @GetMapping("/getDepartmentById")
    public Result<DepartmentVO> getDepartmentById(@RequestParam Long id) {
        // 校验部门 ID 是否为空
        if (Objects.isNull(id)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        // 调用领域服务获取部门数据
        DepartmentBO departmentBO = departmentDomainService.getDepartmentById(id);

        // 校验部门数据是否存在
        if (Objects.isNull(departmentBO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_NOT_EXIST);
        }

        // 将 BO 转换为 VO 并返回
        DepartmentVO departmentVO = DepartmentConverter.INSTANCE.boToVO(departmentBO);
        return Result.success(departmentVO);
    }
}
