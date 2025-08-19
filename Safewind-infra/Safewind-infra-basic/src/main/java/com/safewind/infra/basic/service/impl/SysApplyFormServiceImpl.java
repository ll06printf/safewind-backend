package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.SysApplyForm;
import com.safewind.infra.basic.dao.SysApplyFormDao;
import com.safewind.infra.basic.service.SysApplyFormService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请表(SysApplyForm)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:27
 */
@Service("sysApplyFormService")
public class SysApplyFormServiceImpl implements SysApplyFormService {
    @Resource
    private SysApplyFormDao sysApplyFormDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysApplyForm queryById(Long id) {
        return this.sysApplyFormDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysApplyForm insert(SysApplyForm sysApplyForm) {
        this.sysApplyFormDao.insert(sysApplyForm);
        return sysApplyForm;
    }

    /**
     * 修改数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    @Override
    public SysApplyForm update(SysApplyForm sysApplyForm) {
        this.sysApplyFormDao.update(sysApplyForm);
        return this.queryById(sysApplyForm.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysApplyFormDao.deleteById(id) > 0;
    }

    @Override
    public boolean softDeleteById(Long id) {
        return this.sysApplyFormDao.softDeleteById(id) > 0;
    }

    /**
     * 分页查询申请列表
     *
     * @param sysApplyForm 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @Override
    public PageResult<SysApplyForm> queryPage(SysApplyForm sysApplyForm, Long pageNum, Long pageSize) {
        // 计算偏移量
        Long offset = PageUtils.getOffset(pageNum, pageSize);

        // 查询数据
        List<SysApplyForm> applyFormList = sysApplyFormDao.queryAllByLimit(sysApplyForm, offset, pageSize);

        // 查询总数
        long total = sysApplyFormDao.count(sysApplyForm);

        // 计算总页数
        long totalPages = PageUtils.getTotalPage(total, pageSize);

        return PageResult.<SysApplyForm>builder()
                .data(applyFormList)
                .totalSize(total)
                .totalPages(totalPages)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    @Override
    public SysApplyForm queryByStudentId(String studentId) {
        return sysApplyFormDao.queryByStudentId(studentId);
    }
}
