package com.projecttypea.typea.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MailMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String toEmail;
    private String subject;
    private String body;
    private String pathToAttachement;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPathToAttachement() {
        return pathToAttachement;
    }

    public void setPathToAttachement(String pathToAttachement) {
        this.pathToAttachement = pathToAttachement;
    }

    public MailMessages() {
    }

}
