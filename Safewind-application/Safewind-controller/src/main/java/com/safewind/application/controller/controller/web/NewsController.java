package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.NewsConverter;
import com.safewind.application.controller.vo.NewsVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.NewsExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsListBO;
import com.safewind.domain.bo.NewsQueryBO;
import com.safewind.domain.service.NewsDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:31
 * @description: 官网-新闻模块
 */
@RestController
@RequestMapping("/ws-news")
public class NewsController {

    @Autowired
    private NewsDomainService newsDomainService;

    @ApiOperationLog(description = "首页查看最新新闻")
    @GetMapping("/getLatestNews")
    public Result<List<NewsVO>> getLatestNews(@RequestParam(defaultValue = "5") Long limit) {
        List<NewsListBO> latestNews = newsDomainService.getLatestNews(limit);
        List<NewsVO> newsVOList = NewsConverter.INSTANCE.listBOListToVOList(latestNews);
        return Result.success(newsVOList);
    }

    @ApiOperationLog(description = "所有新闻详情-分页查询")
    @GetMapping("/getAllNews")
    public Result<PageResult<NewsVO>> getAllNews(@RequestParam(defaultValue = "1") Long pageNum,
                                                 @RequestParam(defaultValue = "10") Long pageSize) {
        // 封装查询参数
        NewsQueryBO queryBO = new NewsQueryBO();
        queryBO.setPageNum(pageNum);
        queryBO.setPageSize(pageSize);

        // 调用领域层服务
        PageResult<NewsBO> pageResult = newsDomainService.queryNewsPage(queryBO);
        // 转换为VO
        List<NewsVO> voList = NewsConverter.INSTANCE.boListToVOList(pageResult.getData());
        // 封装返回结果
        PageResult<NewsVO> result = PageResult.<NewsVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    @ApiOperationLog(description = "根据ID获取新闻详情")
    @GetMapping("/getNewsById")
    public Result<NewsVO> getNewsById(@RequestParam Long id) {
        // 参数校验
        if (id == null){
            throw new BizException(NewsExceptionEnum.ID_NOT_NULL);
        }
        NewsBO newsBO = newsDomainService.getNewsById(id);
        if (newsBO == null) {
            throw new BizException(NewsExceptionEnum.NEWS_IS_NULL);
        }
        NewsVO newsVO = NewsConverter.INSTANCE.boToVO(newsBO);
        return Result.success(newsVO);
    }
}
