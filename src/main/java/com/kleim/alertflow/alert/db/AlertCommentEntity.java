package com.kleim.alertflow.alert.db;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "alert_comments")
public class AlertCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "createdAt", nullable = false)
    private OffsetDateTime createdAt;
    @Column(nullable = false)
    private Long analystId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_id", nullable = false)
    private AlertEntity alert;

    public AlertCommentEntity() {
    }

    public AlertCommentEntity(Long id, String comment, OffsetDateTime createdAt, Long analystId, AlertEntity alert) {
        this.id = id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.analystId = analystId;
        this.alert = alert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAnalystId() {
        return analystId;
    }

    public void setAnalystId(Long analystId) {
        this.analystId = analystId;
    }

    public AlertEntity getAlert() {
        return alert;
    }

    public void setAlert(AlertEntity alert) {
        this.alert = alert;
    }
}