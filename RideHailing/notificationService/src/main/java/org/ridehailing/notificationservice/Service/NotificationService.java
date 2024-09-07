package org.ridehailing.notificationservice.Service;

import org.ridehailing.notificationservice.Config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void sendNotification(String email) {

        System.out.println("Notification sent to driver: " + email);
    }

}
