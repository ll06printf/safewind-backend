package com.safewind.application.controller.controller.common;


import com.safewind.application.controller.vo.CaptchaVO;
import com.safewind.common.enums.ResultCodeEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.utils.RedisUtil;
import com.safewind.common.utils.Result;
import com.safewind.common.uuid.IdUtils;
import com.safewind.common.constants.CommonRedisConstant;
import com.safewind.infra.security.captcha.CaptchaResult;
import com.safewind.infra.security.captcha.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * @Author: Darven
 * @CreateTime: 2025-05-24  21:10
 * @Description: 验证码
 */
@Slf4j
@RestController
public class CaptchaController {

    @Autowired
    private CaptchaUtil captchaUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result<CaptchaVO> getCode(){
        // 生成uuid
        String uuid = IdUtils.simpleUUID();
        String captchaCodeKey = CommonRedisConstant.getCaptchaCodeKey(uuid);

        // 获取验证码
        CaptchaResult captchaResult = captchaUtil.getCaptchaResult();

        // 存储redis
        redisUtil.setCacheObject(captchaCodeKey,
                captchaResult.getCode(),
                CommonRedisConstant.CAPTCHA_EXPIRATION,
                TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(captchaResult.getImage(), "jpg", os);
        } catch (IOException e) {
            throw new BizException(ResultCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return Result.success(CaptchaVO.builder()
                .uuid(uuid)
                .img(Base64Utils.encodeToString(os.toByteArray()))
                .build());
    }
}
