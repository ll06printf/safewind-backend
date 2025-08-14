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

    EXISTS_STATUS("0","正常/启用"),
    DELETE_STATUS("2","删除/停用");

    private final String status;

    private final String desc;
}
