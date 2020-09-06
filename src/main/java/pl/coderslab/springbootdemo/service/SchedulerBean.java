package pl.coderslab.springbootdemo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerBean {

    @Scheduled(fixedRate = 1000)
    public void scheduledAction() {
        System.out.println("test");
    }

}
