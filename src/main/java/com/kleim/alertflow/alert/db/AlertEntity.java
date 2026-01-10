package com.kleim.alertflow.alert.db;

import com.kleim.alertflow.alert.AlertCategory;
import com.kleim.alertflow.alert.AlertStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "alerts")
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "alert")
    private List<AlertCommentEntity> comments;

    @Column(name = "createdDate", nullable = false)
    private OffsetDateTime createDate;

    @Column(name = "closeDate")
    private OffsetDateTime closeDate;

    private Long analystId;
    private Long locationId;
    @Enumerated(EnumType.STRING)
    private AlertCategory category;
    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    public AlertEntity() {
    }

    public AlertEntity(Long id, String name, String description, List<AlertCommentEntity> comments, OffsetDateTime createDate, OffsetDateTime closeDate, Long analystId, Long locationId, AlertCategory category, AlertStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.comments = comments;
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

    public List<AlertCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<AlertCommentEntity> comment) {
        this.comments = comment;
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
}
