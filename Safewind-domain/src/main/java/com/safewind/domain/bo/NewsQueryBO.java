package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:18
 * @description: 新闻查询领域对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsQueryBO extends Page {
    /**
     * 新闻标题（模糊查询）
     */
    private String title;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
