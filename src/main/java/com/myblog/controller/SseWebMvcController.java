package com.myblog.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class SseWebMvcController {
    private final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
    private SseEmitter sseEmitter;
    // 返回值类型为text/event-stream
    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createConnection() {
        // 创建sseEmiter，超时时间15秒
        sseEmitter = new SseEmitter(15 * 1000L);
        // 循环向客户端推送消息
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            if (sseEmitter != null) {
                try {
                    // 若携带id项，如果出现异常断开连接，则客户端会携带最后一次的id向服务器发起请求
                    sseEmitter.send(SseEmitter.event().id(UUID.randomUUID().toString()).name("message").data(UUID.randomUUID()));
                } catch (IOException e) {
                    throw new RuntimeException("连接超时，重新建立连接");
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        return sseEmitter;
    }
}
