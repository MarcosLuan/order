package com.order.service;

import com.order.model.UserCreate;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailServiceInterface {

    JavaMailSender sendEmail(UserCreate user);
}
