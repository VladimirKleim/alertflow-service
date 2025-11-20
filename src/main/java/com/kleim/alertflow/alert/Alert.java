package com.kleim.alertflow.alert;

import com.kleim.alertflow.alert.db.AlertCommentEntity;
import com.kleim.alertflow.alert.domain.AlertComment;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class Alert {
    private Long id;
    private String name;
    private String description;
    private List<AlertComment> comment;
    private OffsetDateTime createDate;
    private OffsetDateTime closeDate;
    private Long analystId;
    private Long locationId;
    private AlertCategory category;
    private AlertStatus status;

    public Alert() {
    }

    public Alert(Long id, String name, String description, List<AlertComment> comment, OffsetDateTime createDate, OffsetDateTime closeDate, Long analystId, Long locationId, AlertCategory category, AlertStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.analystId = analystId;
        this.locationId = locationId;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AlertComment> getComment() {
        return comment;
    }

    public void setComment(List<AlertComment> comment) {
        this.comment = comment;
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public OffsetDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(OffsetDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public Long getAnalystId() {
        return analystId;
    }

    public void setAnalystId(Long analystId) {
        this.analystId = analystId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public AlertCategory getCategory() {
        return category;
    }

    public void setCategory(AlertCategory category) {
        this.category = category;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public void setStatus(AlertStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return Objects.equals(id, alert.id) && Objects.equals(name, alert.name) && Objects.equals(description, alert.description) && Objects.equals(createDate, alert.createDate) && Objects.equals(closeDate, alert.closeDate) && Objects.equals(analystId, alert.analystId) && Objects.equals(locationId, alert.locationId) && category == alert.category && status == alert.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createDate, closeDate, analystId, locationId, category, status);
    }
}