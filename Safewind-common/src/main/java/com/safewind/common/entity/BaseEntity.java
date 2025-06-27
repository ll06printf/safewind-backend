package com.safewind.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  21:16
 * @Description: 基础类
 */
@Data
public class BaseEntity {
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
