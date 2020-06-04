package org.saclex.demo.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Feedback {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
