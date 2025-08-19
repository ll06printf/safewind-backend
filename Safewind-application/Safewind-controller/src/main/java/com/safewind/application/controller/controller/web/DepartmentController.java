package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.DepartmentConverter;
import com.safewind.application.controller.vo.DepartmentVO;
import com.safewind.common.annotation.Anonymous;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.service.DepartmentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:35
 * @description: 官网-部门介绍
 */
@Anonymous
@RestController
@RequestMapping("/ws-department")
public class DepartmentController {

    @Autowired
    private DepartmentDomainService departmentDomainService;

    @ApiOperationLog(description = "获取所有部门信息")
    @GetMapping("/getAllDepartments")
    public Result<List<DepartmentVO>> getAllDepartments() {
        // 调用领域服务获取所有部门数据
        List<DepartmentBO> departmentBOList = departmentDomainService.getAllDepartments();

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<DepartmentVO> departmentVOList = DepartmentConverter.INSTANCE.boListToVOList(departmentBOList);

        // 返回成功结果
        return Result.success(departmentVOList);
    }

    @ApiOperationLog(description = "根据ID获取部门信息")
    @GetMapping("/getDepartmentById")
    public Result<DepartmentVO> getDepartmentById(Long id) {
        return null;
    }
}
