package org.ridehailing.reportingservice.Repository;

import org.ridehailing.reportingservice.Entity.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
}
