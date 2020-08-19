package com.example.jpastudy.support.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 * description
 *
 * @author : jkkim
 */
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(length = 20)
    private String createdById;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(length = 20)
    private String updatedById;

}
