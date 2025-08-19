package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.WallConverter;
import com.safewind.application.controller.dto.WallDTO;
import com.safewind.application.controller.vo.WsWallVO;
import com.safewind.common.annotation.Anonymous;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.WallExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallListBO;
import com.safewind.domain.service.WallDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 官网弹幕墙控制器
 * 提供官网弹幕墙模块的相关接口，包括最新弹幕查询以及根据 ID 获取弹幕详情。
 *
 * @author: Darven
 * @createTime: 2025-08-01  18:32
 */
@Anonymous
@RestController
@RequestMapping("/ws-wall")
public class WallController {

    @Autowired
    private WallDomainService wallDomainService; // 弹幕墙领域服务，处理核心业务逻辑

    /**
     * 获取弹幕墙数据
     *
     * @param limit 查询数量限制，默认值为 50
     * @return 包含最新弹幕的视图对象列表
     */
    @ApiOperationLog(description = "获取弹幕墙数据")
    @GetMapping("/getWalls")
    public Result<List<WsWallVO>> getWalls(@RequestParam(defaultValue = "50") Integer limit) {
        // 调用领域服务获取最新弹幕数据
        List<WallListBO> latestWalls = wallDomainService.getLatestWalls(limit);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<WsWallVO> wallVOList = WallConverter.INSTANCE.listBOListToVOList(latestWalls);

        // 返回成功结果
        return Result.success(wallVOList);
    }

    /**
     * 根据 ID 获取弹幕详情
     *
     * @param id 弹幕 ID
     * @return 弹幕的视图对象
     * @throws BizException 如果传入的弹幕 ID 为空或未找到对应弹幕，则抛出业务异常
     */
    @ApiOperationLog(description = "根据ID获取弹幕详情")
    @GetMapping("/getWallById")
    public Result<WsWallVO> getWallById(@RequestParam Long id) {
        // 校验弹幕 ID 是否为空
        if (Objects.isNull(id)) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        // 调用领域服务获取弹幕数据
        WallBO wallBO = wallDomainService.getWallById(id);

        // 校验弹幕数据是否存在
        if (Objects.isNull(wallBO)) {
            throw new BizException(WallExceptionEnum.WALL_NOT_EXIST);
        }

        // 将 BO 转换为 VO 并返回
        WsWallVO wallVO = WallConverter.INSTANCE.boToVO(wallBO);
        return Result.success(wallVO);
    }

    @ApiOperationLog(description = "添加弹幕")
    @PostMapping("/addWall")
    public Result<WsWallVO> addWall(@RequestBody WallDTO wallDTO) {
        // 校验传入的弹幕数据是否为空
        if (Objects.isNull(wallDTO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }

        // 将 DTO 转换为 BO 对象
        WallBO wallBO = WallConverter.INSTANCE.dtoToBO(wallDTO);

        // 调用领域服务保存弹幕数据
        WallBO savedWall = wallDomainService.addWall(wallBO);

        // 将保存后的 BO 转换为 VO 并返回
        WsWallVO wallVO = WallConverter.INSTANCE.boToVO(savedWall);
        return Result.success(wallVO);
    }
}
