package com.myblog.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.myblog.entity.ExcelDemo;
import com.myblog.model.dto.UserDetailsDTO;
import com.myblog.model.dto.UserInfoDTO;
import com.myblog.model.vo.ResultVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Api(tags = "Excel模块")
@RestController
public class ExcelController {
    private static final Logger log = LoggerFactory.getLogger(ExcelController.class);
    //内容样式策略
    WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
    //头策略使用默认
    WriteCellStyle headWriteCellStyle = new WriteCellStyle();

    @GetMapping("/excel/exportModel")
    public void exportModel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter writer = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet = EasyExcel
                .writerSheet(0, fileName)
                .head(ExcelDemo.class)
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle))
                .useDefaultStyle(true)
                .relativeHeadRowIndex(1)
                .build();
        writer.write(null, sheet);
        writer.finish();
    }
}
