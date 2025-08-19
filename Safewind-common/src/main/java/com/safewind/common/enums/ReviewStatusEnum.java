package com.safewind.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-08-15  16:22
 * @description: 审核状态
 */
@Getter
@AllArgsConstructor
public enum ReviewStatusEnum {

    WAIT_REVIEW(0, "待审核"),
    REVIEW_PASS(1, "审核通过"),
    REVIEW_REJECT(2, "审核拒绝"),
    ;

    private final Integer code;
    private final String message;
}
