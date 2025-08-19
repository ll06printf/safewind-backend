package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (SysReview)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysReview extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 607664505332440252L;

    private Long id;

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

