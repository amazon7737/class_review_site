package com.dsu_review_api.infrastructure.config;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createTime = now;
        updateTime = now;
    }

    @PreUpdate
    public void preUpdate() {

        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

}
