package pl.coderslab.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.springbootdemo.service.EmailService;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(path = "email/send")
    String showSendEmailForm() {
        return "email/send";
    }

    @PostMapping(path = "email/send")
    String sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text) {
        emailService.sendEmail(to, subject, text);
        return "email/success";
    }
}
