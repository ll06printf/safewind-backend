package com.safewind.domain.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:17
 * @description: 申请表业务对象
 *  * 用于领域服务层处理申请相关的业务逻辑
 */
@Data
public class ApplyFormBO {
    /**
     * 申请记录ID，主键
     */
    private Long id;

    /**
     * 学生学号，唯一标识
     */
    private String studentId;

    /**
     * 年级，如：2025
     */
    private String grade;

    /**
     * 专业名称
     */
    private String speciality;

    /**
     * 学院名称
     */
    private String faculty;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别，1-男，0-女
     */
    private Integer sex;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 申请部门ID
     */
    private Long deptId;

    /**
     * 申请部门名称
     */
    private String deptName;

    /**
     * 申请状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer status;

    /**
     * 创建者用户名
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者用户名
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 审核信息
     */
    private ReviewBO review;
}
