package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:18
 * @description: 申请表查询业务对象
 *  * 用于领域服务层处理申请查询的业务逻辑
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApplyFormQueryBO extends Page {
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

