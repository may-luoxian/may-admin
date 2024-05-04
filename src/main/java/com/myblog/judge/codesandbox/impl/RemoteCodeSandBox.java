package com.myblog.judge.codesandbox.impl;

import com.myblog.judge.codesandbox.CodeSandBox;
import com.myblog.judge.codesandbox.model.ExecuteCodeRequest;
import com.myblog.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程调用代码沙箱
 */
public class RemoteCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
