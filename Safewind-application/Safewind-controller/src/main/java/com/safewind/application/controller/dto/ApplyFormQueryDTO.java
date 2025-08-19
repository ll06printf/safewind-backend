package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  00:56
 * @description: TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApplyFormQueryDTO extends Page {
    /**
     * 学生学号，支持模糊查询
     */
    private String studentId;

    /**
     * 学生姓名，支持模糊查询
     */
    private String name;

    /**
     * 申请部门ID
     */
    private Long deptId;

    /**
     * 申请状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer status;
}
