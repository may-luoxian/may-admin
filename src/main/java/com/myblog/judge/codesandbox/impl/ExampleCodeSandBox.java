package com.myblog.judge.codesandbox.impl;

import com.myblog.judge.codesandbox.CodeSandBox;
import com.myblog.judge.codesandbox.model.ExecuteCodeRequest;
import com.myblog.judge.codesandbox.model.ExecuteCodeResponse;
import com.myblog.judge.codesandbox.model.JudgeInfo;

import java.util.Arrays;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例代码沙箱");
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage("示例测试");
        judgeInfo.setMemory(1000L);
        judgeInfo.setTime(10000L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        executeCodeResponse.setOutputList(Arrays.asList("1 3", "2 4"));
        return executeCodeResponse;
    }
}
