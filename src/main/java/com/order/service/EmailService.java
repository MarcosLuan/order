package com.order.service;

import com.order.Enum.SituationEnum;
import com.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Order orderDetails) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(orderDetails.getUserCreate().getEmail());
        message.setSubject("Order Movement");
        if(orderDetails.getSituation().equals(SituationEnum.COMPLETED)) {
            message.setText(messageToEmail(orderDetails));
        }
        if (orderDetails.getSituation().equals(SituationEnum.CANCELED)) {
            message.setText("The order code "+ orderDetails.getId() + " was Canceled!");
        }

        javaMailSender.send(message);
        log.info("E-mail successfully sent");
    }

    public static String messageToEmail(Order orderDetails) {
        return "The order code "+ orderDetails.getId() + " was successfully completed! \n\n"
                + "Item: " + orderDetails.getItem().getName() + "\n"
                + "Quantity: " + orderDetails.getQuantity() + "\n"
                + "User: " + orderDetails.getUserCreate().getName() + "\n"
                + "Date Creation: " + orderDetails.getCreationDate() + "\n\n";
    }
}
