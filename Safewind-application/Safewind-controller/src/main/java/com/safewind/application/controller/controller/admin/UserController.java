package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.dto.UserLoginDTO;
import com.safewind.application.controller.vo.UserLoginVO;
import com.safewind.application.controller.vo.UserVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.UserBO;
import com.safewind.domain.service.UserDomainService;
import com.safewind.infra.security.service.SysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        UserVO userVO = getUserVO(userBO);
        log.info("==> getUserInfo获得信息={}",userVO);
        return Result.success(userVO);
    }

    /**
     * UserBO->UserVO
     *
     * @param userBO 领域实体类
     * @return 返回实体类
     * */
    private UserVO getUserVO(UserBO userBO) {
        UserVO userVO = UserVO.builder()
                .userId(userBO.getUserId())
                .studentId(userBO.getStudentId())
                .email(userBO.getEmail())
                .nickname(userBO.getUserInfo().getNickname())
                .avatar(userBO.getUserInfo().getAvatar())
                .grade(userBO.getUserInfo().getGrade())
                .speciality(userBO.getUserInfo().getSpeciality())
                .faculty(userBO.getUserInfo().getFaculty())
                .userInfoName(userBO.getUserInfo().getName())
                .sex(userBO.getUserInfo().getSex())
                .className(userBO.getUserInfo().getClassName())
                .roleName(userBO.getRole().getRoleName())
                .roleKey(userBO.getRole().getRoleKey())
                .deptName(userBO.getDept().getName())
                .build();
        return userVO;
    }
}
