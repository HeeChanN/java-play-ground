package com.example.practice_spring.ex1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduledTasks {

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRateString = "${schedules.report.rate:5000}")
    public void reportCurrentTime() {
        log.info("The time is now {}", LocalDateTime.now().format(TIME_FMT));
    }

    @Scheduled(cron = "${schedules.backup.cron:0 30 3 * * *}", zone = "Asia/Seoul")
    public void backup() {
        log.info("backup() executed");
    }

    @Scheduled(initialDelayString = "${schedules.cleanup.initial:30000}",
            fixedDelayString   = "${schedules.cleanup.delay:60000}")
    public void cleanup() {
        log.info("cleanup() executed");
    }
}
