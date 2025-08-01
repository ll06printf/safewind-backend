package com.safewind.application.controller.controller.web;

import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:31
 * @description: 官网-新闻模块
 */
@RestController
@RequestMapping("/ws-news")
public class NewsController {
    @ApiOperationLog(description = "首页查看新闻")
    @GetMapping("/getNews")
    public Result<List<Objects>> getActivity(){
        return null;
    }

    @ApiOperationLog(description = "所有新闻详情-分页查询")
    @GetMapping("/getAllNews")
    public Result<PageResult<Objects>> getAllActivity(){
        return null;
    }
}
