package com.kleim.alertflow.location.repository;

import org.hibernate.resource.transaction.backend.jdbc.spi.JdbcResourceTransaction;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query("""
          SELECT l FROM LocationEntity l
          WHERE (:name IS NULL OR l.name ILIKE %:name%) AND
          (:minWorkers IS NULL OR l.workers >= :minWorkers) AND
          (:maxWorkers IS NULL OR l.workers <= :maxWorkers)
           """)
    List<LocationEntity> searchLocation(
            @Param("name") String name,
            @Param("minWorkers") Integer minWorkers,
            @Param("maxWorkers") Integer maxWorkers
    );
}
