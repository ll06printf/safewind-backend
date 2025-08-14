package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.NewsConverter;
import com.safewind.application.controller.dto.NewsDTO;
import com.safewind.application.controller.dto.NewsQueryDTO;
import com.safewind.application.controller.vo.NewsVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.NewsExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsQueryBO;
import com.safewind.domain.service.NewsDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-11  22:18
 * @description: 新闻模块-管理页面
 */
@RestController
@RequestMapping("/admin-news")
public class NewsAdminController {

    @Autowired
    private NewsDomainService newsDomainService;

    @ApiOperationLog(description = "新闻列表-分页查询")
    @PostMapping("/getNews")
    public Result<PageResult<NewsVO>> getNews(@RequestBody NewsQueryDTO queryDTO) {
        // 类型转化
        NewsQueryBO queryBO = NewsConverter.INSTANCE.queryDTOToQueryBO(queryDTO);
        // 分页查询
        PageResult<NewsBO> pageResult = newsDomainService.queryNewsPage(queryBO);
        List<NewsVO> voList = NewsConverter.INSTANCE.boListToVOList(pageResult.getData());
        // 封装返回结果
        PageResult<NewsVO> result=PageResult.<NewsVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    @ApiOperationLog(description = "添加新闻")
    @PostMapping("/addNews")
    public Result<NewsVO> addNews(@RequestBody NewsDTO newsDTO) {
        // 在jsonUtils统一设置时间格式
        NewsBO newsBO = NewsConverter.INSTANCE.dtoToBO(newsDTO);
        NewsBO savedNews = newsDomainService.addNews(newsBO);
        NewsVO newsVO = NewsConverter.INSTANCE.boToVO(savedNews);
        return Result.success(newsVO);
    }

    @ApiOperationLog(description = "修改新闻")
    @PostMapping("/updateNews")
    public Result<NewsVO> updateNews(@RequestBody NewsDTO newsDTO) {
        // 检查参数
        if (newsDTO.getId() == null) {
            throw new BizException(NewsExceptionEnum.ID_NOT_NULL);
        }
        // 类型转化
        NewsBO newsBO = NewsConverter.INSTANCE.dtoToBO(newsDTO);
        NewsBO updatedNews = newsDomainService.updateNews(newsBO);
        NewsVO newsVO = NewsConverter.INSTANCE.boToVO(updatedNews);
        return Result.success(newsVO);
    }

    @ApiOperationLog(description = "删除新闻")
    @PostMapping("/deleteNews")
    public Result<Boolean> deleteNews(@RequestBody NewsDTO newsDTO) {
        // 检查参数
        if (newsDTO == null || newsDTO.getId() == null) {
            throw new BizException(NewsExceptionEnum.ID_NOT_NULL);
        }
        Long id = newsDTO.getId();
        Boolean result = newsDomainService.deleteNews(id);
        return Result.success(result);
    }

    @ApiOperationLog(description = "根据ID获取新闻详情")
    @GetMapping("/getNewsById")
    public Result<NewsVO> getNewsById(@RequestParam Long id) {
        // 检查参数
        if (id == null) {
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
