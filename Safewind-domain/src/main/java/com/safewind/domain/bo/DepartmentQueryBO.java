package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  00:59
 * @description:  * 部门查询业务对象
 *  * 用于领域服务层处理部门查询的业务逻辑
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQueryBO extends Page {
    /**
     * 部门名称，支持模糊查询
     */
    private String name;

    /**
     * 创建者用户名
     */
    private String createBy;

    /**
     * 查询开始时间，用于时间范围筛选
     */
    private LocalDateTime startTime;

    /**
     * 查询结束时间，用于时间范围筛选
     */
    private LocalDateTime endTime;
}
