package com.example.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyblogApplicationTests {
    public static void main(String[] args) {

    }
    void contextLoads(int n) {
        int i;
        long f = n;
        for (i = 1; i <= n; i++) {
            f = f * 2;
        }
    }

}
