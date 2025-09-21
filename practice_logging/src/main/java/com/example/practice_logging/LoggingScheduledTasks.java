package com.example.practice_logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class LoggingScheduledTasks {

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRateString = "${schedules.report.rate:5000}")
    public void reportCurrentTime() {
        log.info("The time is now {}", LocalDateTime.now().format(TIME_FMT));
    }

}
