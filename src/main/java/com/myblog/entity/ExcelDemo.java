package com.myblog.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDemo {
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("学号")
    private Integer studentNo;
    @ExcelProperty("学生姓名")
    private String studentName;
    @ExcelProperty("班号")
    private Integer classNo;
    @ExcelProperty("班名")
    private String className;
}
