package com.safewind.application.controller.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-09  15:30
 * @description: 用户部门信息
 */
@Data
@Builder
public class UserDeptVO {
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String name;
}
