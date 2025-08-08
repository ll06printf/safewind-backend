package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 用户和角色关联表(SysUserRole)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -38956115038317976L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
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

