package org.example.expert.aop.log.repository;

import org.example.expert.aop.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
