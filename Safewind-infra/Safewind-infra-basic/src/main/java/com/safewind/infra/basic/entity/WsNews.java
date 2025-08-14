package com.safewind.infra.basic.entity;

import com.safewind.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 新闻(WsNews)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Setter
@Getter
public class WsNews extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -49098484929198511L;

    private Long id;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 新闻内容/html
     * */
    private String htmlContent;
    /**
     * 封面
     * */
    private String coverImg;
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

