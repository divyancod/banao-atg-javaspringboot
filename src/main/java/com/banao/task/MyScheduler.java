package com.banao.task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class MyScheduler {
    Logger logger = LoggerFactory.getLogger(MyScheduler.class);
    @PostConstruct
    @Scheduled(cron = "0 0 10 * * *") //second, minute, hour, day, month, and weekday.
    public void schedulePrint() {
        logger.info("HELLO ATG");
    }
}
