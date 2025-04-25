package com.example.practice_spring.ex1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;


import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
        // 테스트용 초단기 주기 오버라이드
        "schedules.report.rate=100",           // 0.1초 간격
        "schedules.backup.cron=*/1 * * * * *", // 매 1초
        "schedules.cleanup.initial=0",         // 지연 없음
        "schedules.cleanup.delay=200"          // 0.2초 간격
})
@SuppressWarnings("NonAsciiCharacters")
public class ScheduledTasksTest {

    @MockitoSpyBean
    ScheduledTasks scheduledTasks;

    @Test
    void 시간출력_ScheduledTask_최소_5번_실행() {
        await().atMost(Duration.ofSeconds(1))
                .untilAsserted(() ->
                        verify(scheduledTasks, atLeast(5)).reportCurrentTime());
    }

    @Test
    void backUp_ScheduledTask_최소_1번_실행() {
        await().atMost(Duration.ofSeconds(2))
                .untilAsserted(() ->
                        verify(scheduledTasks, atLeastOnce()).backup());
    }

    @Test
    void cleanup_ScheduledTask_최소_1번_실행() {
        await().atMost(Duration.ofSeconds(1))
                .untilAsserted(() ->
                        verify(scheduledTasks, atLeast(3)).cleanup());
    }
}
