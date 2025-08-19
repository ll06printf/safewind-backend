package com.safewind.application.controller.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:10
 * @description:
 *  部门数据传输对象
 *  用于接收前端传递的部门信息，包含部门的基本属性
 */
@Data
public class DepartmentDTO {
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
