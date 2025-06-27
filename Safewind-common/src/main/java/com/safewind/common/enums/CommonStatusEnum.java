package com.safewind.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-06-27  17:34
 * @description: 通用状态枚举
 */
@AllArgsConstructor
@Getter
public enum CommonStatusEnum {

    DELETE_STATUS("0","删除/停用"),
    EXISTS_STATUS("2","正常/启用");

    private final String status;

    private final String desc;
}
