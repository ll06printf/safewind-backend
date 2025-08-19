package com.safewind.application.controller.dto;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-16  03:11
 * @description: 审核字段
 */
@Data
public class ReviewDTO {
    /**
     * 申请表id
     * */
    private Long id;

    private Integer status;

    private String remark;
}
