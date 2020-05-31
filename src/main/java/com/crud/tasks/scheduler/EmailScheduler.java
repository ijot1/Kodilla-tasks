package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleMailService simpleMailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *") //everyday at 10 o'clock
    /*<second> <minute> <hour> <day-of-month> <month> <day-of-week> <year>; <year> field is optional*/
//    @Scheduled(fixedDelay = 300000) //300000 = 5 min.
    public void sendInformationEmail() {
        long size = taskRepository.count();
        Mail mail = new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "");
        mail.setMessage("Currently you got: " + size + (size != 1 ? " tasks in database" : " task in database"));
        simpleMailService.send(mail);
    }
}
