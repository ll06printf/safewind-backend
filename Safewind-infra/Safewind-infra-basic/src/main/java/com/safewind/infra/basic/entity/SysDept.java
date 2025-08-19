package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 部门表(SysDept)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDept extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 929177112440596513L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 部门名字
     */
    private String name;
    /**
     * 部门介绍富文本
     */
    private String content;
    /**
     * 部门图片URL
     */
    private String imageUrl;
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
    /**
     * 是否外链，0-否，1-是
     */
    private String isLink;

    /**
     * 外链地址
     */
    private String externalLink;

    /**
     * 部门介绍 （html）
     */
    private String introduction;

}

