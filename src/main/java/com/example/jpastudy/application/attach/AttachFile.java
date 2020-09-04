package com.example.jpastudy.application.attach;

import com.example.jpastudy.support.entity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * description
 *
 * @author : jkkim
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ATTACH_FILE")
public class AttachFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attach_seq")
    private Long attachSeq;
    private String attachFileName;
    private Long attachFileSize;
    private String attachFileRoute;

    @Builder
    public AttachFile(Long attachSeq, String attachFileName, Long attachFileSize, String attachFileRoute) {
        this.attachSeq = attachSeq;
        this.attachFileName = attachFileName;
        this.attachFileSize = attachFileSize;
        this.attachFileRoute = attachFileRoute;
    }
}
