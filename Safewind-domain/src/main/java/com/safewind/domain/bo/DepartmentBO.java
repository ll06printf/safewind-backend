package com.safewind.domain.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  00:58
 * @description: 部门业务对象
 *  * 用于领域服务层处理部门相关的业务逻辑
 */
@Data
public class DepartmentBO {
    /**
     * 部门ID，主键
     */
    private Long id;

    /**
     * 部门名称，如：技术部、宣传部等
     */
    private String name;

    /**
     * 部门介绍内容，支持富文本格式
     */
    private String content;

    /**
     * 部门图片URL
     */
    private String imageUrl;

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
