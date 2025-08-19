package com.safewind.application.controller.controller.web;

import com.safewind.common.annotation.ApiOperationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Darven
 * @createTime: 2025-08-18  23:45
 * @description: 首页控制器
 */
@RestController
@RequestMapping("/ws-index")
public class IndexController {

    @ApiOperationLog(description = "获取首页封面")
    @GetMapping("/getIndexCover")
    public String getIndexCover() {
        return "首页封面";
    }
}
