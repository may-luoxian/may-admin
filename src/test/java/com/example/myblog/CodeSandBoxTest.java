package com.example.myblog;

import com.myblog.enums.QuestionSubmitLanguageEnum;
import com.myblog.judge.codesandbox.CodeSandBox;
import com.myblog.judge.codesandbox.CodeSandBoxFactory;
import com.myblog.judge.codesandbox.impl.ExampleCodeSandBox;
import com.myblog.judge.codesandbox.model.ExecuteCodeRequest;
import com.myblog.judge.codesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest(classes = CodeSandBox.class)
public class CodeSandBoxTest {
    @Value("${codeSandBox.type:example}")
    private String type;

    @Test
    void executeCode() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
            List<String> strings = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                    .code("int main() {}")
                    .inputList(strings)
                    .build();
            ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
            Assertions.assertNotNull(executeCodeResponse);
        }
    }


    @Test
    void executeCodeByValue() {
            CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
            List<String> strings = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                    .code("int main() {}")
                    .inputList(strings)
                    .build();
            ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
            Assertions.assertNotNull(executeCodeResponse);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
            List<String> strings = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .language(QuestionSubmitLanguageEnum.JAVA.getValue())
                    .code("int main() {}")
                    .inputList(strings)
                    .build();
            ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
            Assertions.assertNotNull(executeCodeResponse);
        }
    }
}
