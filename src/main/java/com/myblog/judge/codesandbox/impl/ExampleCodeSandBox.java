package com.myblog.judge.codesandbox.impl;

import com.myblog.judge.codesandbox.CodeSandBox;
import com.myblog.judge.codesandbox.model.ExecuteCodeRequest;
import com.myblog.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例代码沙箱");
        return null;
    }
}
