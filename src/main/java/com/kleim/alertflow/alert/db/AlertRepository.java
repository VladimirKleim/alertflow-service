package com.kleim.alertflow.alert.db;

import com.kleim.alertflow.alert.AlertStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

    @EntityGraph(attributePaths = {"comments"})
    @Query("""
           SELECT ae FROM AlertEntity ae
           """)
    List<AlertEntity> findAlertsWithComments();

    @Query("""
          UPDATE AlertEntity a
          SET a.status = :alertStatus
          WHERE a.id = :alertId
          """)
    @Modifying
    void modifyAlertionStatus(
            @Param("alertId") Long alertId,
            @Param("alertStatus") AlertStatus alertStatus);
}

