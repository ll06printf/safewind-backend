package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  00:53
 * @description: 部门查询数据传输对象
 *  用于封装部门查询的筛选条件和分页参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQueryDTO extends Page {
    /**
     * 部门名称，支持模糊查询
     */
    private String name;

    /**
     * 查询开始时间，用于时间范围筛选
     */
    private LocalDateTime startTime;

    /**
     * 查询结束时间，用于时间范围筛选
     */
    private LocalDateTime endTime;
}
