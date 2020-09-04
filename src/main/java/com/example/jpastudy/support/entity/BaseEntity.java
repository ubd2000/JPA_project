package com.example.jpastudy.support.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * description
 *
 * @author : jkkim
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//추상 클래스
public abstract class BaseEntity {

    @CreatedDate // Entity 생성시 자동으로 날짜세팅
    private LocalDateTime createdDate;
    @LastModifiedDate // Entity 수정시 자동으로 날짜세팅
    private LocalDateTime modifiedDate;

}
