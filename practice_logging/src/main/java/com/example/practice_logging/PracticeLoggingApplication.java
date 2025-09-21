package com.example.practice_logging;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PracticeLoggingApplication {

	//static final Log log = LogFactory.getLog(PracticeLoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PracticeLoggingApplication.class, args);
	}
}

/*
* 	private static final Logger logger = LoggerFactory.getLogger(PracticeLoggingApplication.class);


	public static void main(String[] args) {
		logger.info("Example log from {}", PracticeLoggingApplication.class.getSimpleName());
		SpringApplication.run(PracticeLoggingApplication.class, args);
	}
static final Log log = LogFactory.getLog(PracticeLoggingApplication.class);


	public static void main(String[] args) {
		System.out.println(log.getClass().getName());
		SpringApplication.run(PracticeLoggingApplication.class, args);
	}
*
* */
