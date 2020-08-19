package com.example.jpastudy.application.attach;

import com.example.jpastudy.application.board.Board;
import com.example.jpastudy.support.entity.BaseEntity;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * description
 *
 * @author : jkkim
 */
@Entity
@Table(name = "ATTACH_FILE")
@Getter
@Setter
@ToString
public class AttachFile extends BaseEntity {

    @Id
    @Column(name = "attach_seq")
    @GeneratedValue(generator="SharedPrimaryKeyGenerator")
    @GenericGenerator(name="SharedPrimaryKeyGenerator",strategy="foreign",parameters = @Parameter(name="property", value = "board"))
    private Long boardSeq;
    private String attachFileName;
    private String attachFileSize;
    private String attachFileRoute;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "attach_seq",referencedColumnName="board_seq")
    private Board board;

}
