package com.safewind.application.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-15  17:16
 * @description: 审核详情
 */
@Data
public class ReviewVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 申请ID
     */
    private Long applyId;
    /**
     * 0 未审核 1审核通过 2拒绝
     */
    private Integer state;
    /**
     * 备注
     */
    private String remark;
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
