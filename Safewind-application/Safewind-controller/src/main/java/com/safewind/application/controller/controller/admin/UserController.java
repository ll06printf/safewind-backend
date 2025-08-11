package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.MenuConverter;
import com.safewind.application.controller.converter.UserConverter;
import com.safewind.application.controller.dto.UserLoginDTO;
import com.safewind.application.controller.vo.MenuVO;
import com.safewind.application.controller.vo.UserLoginVO;
import com.safewind.application.controller.vo.UserVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;
import com.safewind.domain.bo.UserBO;
import com.safewind.domain.service.MenuDomainService;
import com.safewind.domain.service.UserDomainService;
import com.safewind.infra.security.service.SecurityUtil;
import com.safewind.infra.security.service.SysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-23  23:32
 * @Description: 用户控制层
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private MenuDomainService menuDomainService;

    @ApiOperationLog(description = "登录接口")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){

        String token = loginService.login(userLoginDTO.getUserName(),
                userLoginDTO.getPassword(),
                userLoginDTO.getCode(),
                userLoginDTO.getUuid());

        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setToken(token);
        return Result.success(userLoginVO);
    }

    @ApiOperationLog(description = "注册接口")
    @PostMapping("/register")
    public Result<UserLoginVO> register(@RequestBody UserLoginDTO userLoginDTO){
        return Result.success();
    }

    @ApiOperationLog(description = "获取用户信息")
    @GetMapping("/getLoginUser")
    public Result<String> getLoginUser(){
        return Result.success("成功");
    }

    @ApiOperationLog(description = "用户中心")
    @GetMapping("/getUserInfo")
    public Result<UserVO> getUserInfo(){
        UserBO userBO = userDomainService.getUserInfo();
        log.info("userBO={}", userBO);
        // 抽成方法存放，实体转化
        UserVO userVO = UserConverter.INSTANCE.userBOToVO(userBO);
        log.info("==> getUserInfo获得信息={}",userVO);
        return Result.success(userVO);
    }

    @ApiOperationLog(description = "获取用户路由")
    @GetMapping("/getRoutes")
    public Result<List<MenuVO>> getRoutes(){
        Long userId = SecurityUtil.getUserId();
        List<MenuListBO> menuBOList= userDomainService.getRoutes(userId);
        // 参数转化
        List<MenuVO> menuVOS = MenuConverter.INSTANCE.menuBOListToMenuVOList(menuBOList);
        return Result.success(menuVOS);
    }
}
