package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 海风墙(WsWall)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WsWall extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -51026158277872638L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户头像
     * */
    private String avatar;
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
