package com.myblog.controller;

import com.myblog.model.vo.ResultVO;
import com.myblog.model.vo.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "测试模块")
@RestController
public class TestController {

    @ApiOperation(value = "测试GET请求1")
    @GetMapping(value = "/test/get_request1")
    public ResultVO<?> testGetRequest1(@RequestParam(value = "state") Integer s, @RequestParam(value = "name") String n) {
        return ResultVO.ok(s + " " + n);
    }

    @ApiOperation(value = "测试GET请求2")
    @GetMapping(value = "/test/get_request2/{id}")
    public ResultVO<?> testGetRequest2(@PathVariable(value = "id") Integer id) {
        return ResultVO.ok(id);
    }

    @ApiOperation(value = "测试POST请求1")
    @PostMapping(value = "/test/post_request1")
    public ResultVO<?> testPostRequest1(@RequestBody TestVO testVO) {
        return ResultVO.ok(testVO);
    }

    @ApiOperation(value = "测试POST请求2文件")
    @PostMapping(value = "/test/post_request2")
    public ResultVO<?> testPostRequest2(TestVO testVO) {
        return ResultVO.ok();
    }

    @ApiOperation(value = "上传文件到该项目static目录")
    @PostMapping(value = "/test/upload")
    public ResultVO<?> upload(TestVO testVO) throws IOException {
        String staticPath = ResourceUtils.getURL("classpath:").getPath() + "static";
        File staticCatalogue = new File(staticPath);
        if (!staticCatalogue.exists()) {
            staticCatalogue.mkdir();
        }
        File dest = new File(staticPath, testVO.getName());
        MultipartFile file = testVO.getFile();
        file.transferTo(dest);
        return ResultVO.ok();
    }
}
