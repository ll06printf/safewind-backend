package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 申请表(WsActivity)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WsActivity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 198898345824503188L;

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String brief;
    /**
     * 详情
     */
    private String introduction;
    /**
     * 活动时间
     */
    private LocalDateTime startTime;
    /**
     * 视觉图
     */
    private String picture;
    /**
     * 是否外链
     * */
    private String isLink;
    /**
     * 外链
     * */
    private String externalLink;
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

