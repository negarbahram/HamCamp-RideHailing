package org.ridehailing.reportingservice.Service;

import lombok.RequiredArgsConstructor;
import org.ridehailing.reportingservice.Config.RabbitMQConfig;
import org.ridehailing.reportingservice.Entity.EventLog;
import org.ridehailing.reportingservice.Repository.EventLogRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportingService {
    private final EventLogRepository eventLogRepository;

    @RabbitListener(queues = RabbitMQConfig.REPORTING_QUEUE)
    public void recordEvent(String eventMessage) {
        String[] chunks = eventMessage.split("-");
        EventLog eventLog = new EventLog();
        eventLog.setEventType(chunks[0]);
        eventLog.setEventData(chunks[1]);
        eventLog.setTimestamp(LocalDateTime.now());
        eventLogRepository.save(eventLog);
    }
}
