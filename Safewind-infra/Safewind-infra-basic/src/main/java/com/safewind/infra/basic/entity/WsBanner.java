package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 横幅(WsBanner)实体类
 *
 * @author Darven
 * @since 2025-08-19 00:00:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WsBanner extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 横幅标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 背景图
     */
    private String backgroundPicture;
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
    private java.time.LocalDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private java.time.LocalDateTime updateTime;
} 