package com.example.custom_logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class CustomLoggerTest {

    private CustomLogger logger;

    @BeforeEach
    void setUp() throws Exception {
        logger = CustomLogger.getInstance();
    }

    @Test
    void 싱글톤_검증(){
        //given
        CustomLogger logger2 = CustomLogger.getInstance();

        //when & then
        assertThat(logger).isSameAs(logger2);
    }

    @Test
    void 로그_출력_검증() throws Exception {
        //when
        logger.log("메시지");
    }

    @Test
    void worker_쓰레드_종료_검증() throws Exception {
        logger.log("테스트 로그1");
        logger.log("테스트 로그2");

        // when
        logger.shutdown();

        // then
        Thread worker = logger.getWorkerThread();
        assertThat(worker.isAlive()).isFalse();
    }
}
