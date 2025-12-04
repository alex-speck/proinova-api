package br.com.senac.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false ,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal currentFund;

    @Column(nullable = false)
    private BigDecimal fundGoal;

    @Column(nullable = false)
    private List<String> members;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(optional = false)
    private ActivityArea activityArea;

    @ManyToOne(optional = false)
    private DevStage devStage;

    @ManyToOne(optional = false)
    private User owner;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCurrentFund() {
        return currentFund;
    }

    public void setCurrentFund(BigDecimal currentFund) {
        this.currentFund = currentFund;
    }

    public BigDecimal getFundGoal() {
        return fundGoal;
    }

    public void setFundGoal(BigDecimal fundGoal) {
        this.fundGoal = fundGoal;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public ActivityArea getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(ActivityArea activityArea) {
        this.activityArea = activityArea;
    }

    public DevStage getDevStage() {
        return devStage;
    }

    public void setDevStage(DevStage devStage) {
        this.devStage = devStage;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
