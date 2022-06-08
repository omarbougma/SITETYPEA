package com.projecttypea.typea.ws;

import com.projecttypea.typea.bean.MailMessages;
import com.projecttypea.typea.service.MailMessagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailMessagesWS {
    @Autowired
    MailMessagesService mailMessagesService;

    @PostMapping("/admin/sendmail")
    public int sendMail(@RequestBody MailMessages mailMessages) {
        return mailMessagesService.sendMail(mailMessages);
    }

    @PostMapping("/admin/sendsimplemail")
    public void sendSimpleMail(@RequestBody MailMessages mailMessages) {
        mailMessagesService.sendSimpleMail(mailMessages);
    }

}
