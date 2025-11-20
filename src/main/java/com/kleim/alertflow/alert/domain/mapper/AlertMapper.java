package com.kleim.alertflow.alert.domain.mapper;

import com.kleim.alertflow.alert.Alert;
import com.kleim.alertflow.alert.db.AlertCommentEntity;
import com.kleim.alertflow.alert.db.AlertEntity;
import com.kleim.alertflow.alert.domain.AlertComment;
import org.springframework.stereotype.Component;

@Component
public class AlertMapper {

    public Alert toDomain(AlertEntity alert) {
        return new Alert(
                alert.getId(),
                alert.getName(),
                alert.getDescription(),
                alert.getComments().stream().map(it ->
                        new AlertComment(
                                it.getId(),
                                it.getComment(),
                                it.getCreatedAt(),
                                it.getAnalystId(),
                                alert.getId()
                        )).toList(),
                alert.getCreateDate(),
                alert.getCloseDate(),
                alert.getAnalystId(),
                alert.getLocationId(),
                alert.getCategory(),
                alert.getStatus()
        );
    }

    public AlertEntity toEntity(Alert alert) {
        AlertEntity entity = new AlertEntity();
        entity.setId(alert.getId());
        return new AlertEntity(
                alert.getId(),
                alert.getName(),
                alert.getDescription(),
                alert.getComment().stream().map(it ->
                        new AlertCommentEntity(
                                it.id(),
                                it.comment(),
                                it.createdAt(),
                                it.analystId(),
                                entity
                        )).toList(),
                alert.getCreateDate(),
                alert.getCloseDate(),
                alert.getAnalystId(),
                alert.getLocationId(),
                alert.getCategory(),
                alert.getStatus()
        );
    }
}