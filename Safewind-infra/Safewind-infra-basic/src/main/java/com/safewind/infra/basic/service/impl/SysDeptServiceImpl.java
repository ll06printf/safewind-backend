package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.SysDept;
import com.safewind.infra.basic.dao.SysDeptDao;
import com.safewind.infra.basic.service.SysDeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Resource
    private SysDeptDao sysDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysDept queryById(Long id) {
        return this.sysDeptDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysDept insert(SysDept sysDept) {
        this.sysDeptDao.insert(sysDept);
        return sysDept;
    }

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysDept update(SysDept sysDept) {
        this.sysDeptDao.update(sysDept);
        return this.queryById(sysDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysDeptDao.deleteById(id) > 0;
    }

    /**
     * 分页查询部门列表
     *
     * @param sysDept 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @Override
    public PageResult<SysDept> queryPage(SysDept sysDept, Long pageNum, Long pageSize) {
        // 计算偏移量
        Long offset = PageUtils.getOffset(pageNum, pageSize);

        // 查询数据
        List<SysDept> deptList = sysDeptDao.queryAllByLimit(sysDept, offset, pageSize);

        // 查询总数
        long total = sysDeptDao.count(sysDept);

        // 计算总页数
        long totalPages = PageUtils.getTotalPage(total, pageSize);

        return PageResult.<SysDept>builder()
                .data(deptList)
                .totalSize(total)
                .totalPages(totalPages)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    /**
     * 获取所有部门
     *
     * @return 部门列表
     */
    @Override
    public List<SysDept> getAllDepartments() {
        return sysDeptDao.queryAll();
    }
}
