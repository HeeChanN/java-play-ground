package com.example.custom_logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomLogger {

    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Thread worker;
    private volatile boolean running = true;

    private CustomLogger() {
        worker = new Thread(() -> {
            try {
                while (running) {
                    String msg = logQueue.take();
                    System.out.println(msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        worker.start();
    }

    private static class LoggerHolder {
        private static final CustomLogger INSTANCE = new CustomLogger();
    }

    public static CustomLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    public void log(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(LocalDateTime.now().format(TIME_FMT)).append("] ")
                .append(message);
        boolean success = logQueue.offer(sb.toString());
        if (!success) {
            // TODO: 로그 큐에 넣는거 실패하면 어떻게 할지 처리 로직 생각해보기
        }
    }

    public void shutdown() {
        while (!logQueue.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        running = false;
        worker.interrupt();

        try {
            worker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Thread getWorkerThread() { //쓰레드 종료 검증용
        return worker;
    }
}
