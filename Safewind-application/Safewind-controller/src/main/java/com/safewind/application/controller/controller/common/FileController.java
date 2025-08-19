package com.safewind.application.controller.controller.common;

import com.safewind.application.controller.vo.FileVO;
import com.safewind.common.annotation.Anonymous;
import com.safewind.common.utils.Result;
import com.safewind.domain.service.FileDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  22:42
 * @Description: 文件控制层
 */
@Anonymous
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileDomainService fileDomainService;

    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)  //提交方式是表单提交
    public Result<FileVO> upload(@RequestPart("file")MultipartFile file){
        log.info("文件={}", file.getName());
        FileVO fileVO = new FileVO();
        fileVO.setUrl(fileDomainService.uploadFile(file));
        return Result.success(fileVO);
    }
}
