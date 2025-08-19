package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 申请表(SysApplyForm)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysApplyForm extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 406843624801298500L;

    private Long id;
    /**
     * 学生学号
     */
    private String studentId;
    /**
     * 年级，形如 ‘2025’ 的字符串
     */
    private String grade;
    /**
     * 专业
     */
    private String speciality;
    /**
     * 学院
     */
    private String faculty;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别，“1，男“，”0，女“
     */
    private Integer sex;
    /**
     * 班级
     */
    private String className;
    /**
     * 部门
     */
    private Long deptId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

