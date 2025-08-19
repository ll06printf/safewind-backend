package com.safewind.domain.service.impl;

import com.safewind.common.enums.DepartmentExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.bo.DepartmentQueryBO;
import com.safewind.domain.converter.DepartmentDomainConverter;
import com.safewind.domain.service.DepartmentDomainService;
import com.safewind.infra.basic.dao.SysDeptDao;
import com.safewind.infra.basic.entity.SysDept;
import com.safewind.infra.basic.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:39
 * @description: TODO
 */
@Service
public class DepartmentDomainServiceImpl implements DepartmentDomainService {

    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public PageResult<DepartmentBO> queryDepartmentPage(DepartmentQueryBO queryBO) {
        // 构建查询条件
        SysDept query = new SysDept();
        query.setName(queryBO.getName());

        // 调用基础设施层服务
        PageResult<SysDept> pageResult = sysDeptService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换为领域对象
        List<DepartmentBO> departmentBOList = DepartmentDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        return PageResult.<DepartmentBO>builder()
                .data(departmentBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public DepartmentBO getDepartmentById(Long id) {
        // 校验参数
        if (Objects.isNull(id)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        SysDept sysDept = sysDeptService.queryById(id);
        if (sysDept == null) {
            return null;
        }
        return DepartmentDomainConverter.INSTANCE.entityToBO(sysDept);
    }

    @Override
    public DepartmentBO addDepartment(DepartmentBO departmentBO) {
        // 校验参数
        if (Objects.isNull(departmentBO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_IS_NULL);
        }

        SysDept sysDept = DepartmentDomainConverter.INSTANCE.boToEntity(departmentBO);
        SysDept savedDept = sysDeptService.insert(sysDept);
        return DepartmentDomainConverter.INSTANCE.entityToBO(savedDept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DepartmentBO updateDepartment(DepartmentBO departmentBO) {
        // 校验参数
        if (Objects.isNull(departmentBO)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_IS_NULL);
        }
        if (Objects.isNull(departmentBO.getId())) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        SysDept sysDept = DepartmentDomainConverter.INSTANCE.boToEntity(departmentBO);
        // 检查是否存在
        SysDept sysDeptToUpdate = sysDeptService.queryById(sysDept.getId());
        if (sysDeptToUpdate == null) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_NOT_EXIST);
        }
        SysDept updatedDept = sysDeptService.update(sysDept);
        return DepartmentDomainConverter.INSTANCE.entityToBO(updatedDept);
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        // 校验参数
        if (Objects.isNull(id)) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_ID_NOT_NULL);
        }

        // 检查是否存在
        SysDept sysDept = sysDeptService.queryById(id);
        if (sysDept == null) {
            throw new BizException(DepartmentExceptionEnum.DEPARTMENT_NOT_EXIST);
        }
        return sysDeptService.deleteById(id);
    }

    @Override
    public List<DepartmentBO> getAllDepartments() {
        List<SysDept> allDepts = sysDeptService.getAllDepartments();
        return DepartmentDomainConverter.INSTANCE.entityListToBOList(allDepts);
    }
}
