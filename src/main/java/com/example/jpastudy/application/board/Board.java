package com.example.jpastudy.application.board;

import com.example.jpastudy.support.constant.BoardType;
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
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_seq")
    private Long boardSeq;
    /** 게시판 유형 */
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
    //제목
    private String subJect;
    //내용
    private String contents;
    //조회수
    private Integer viewCnt;
    //파일 ID
    private Long fileId;

    @Column(length = 20)
    private String createdById;

    @Column(length = 20)
    private String updatedById;

    @Builder
    public Board(Long boardSeq, String subJect, String contents, Integer viewCnt, Long fileId, String createById, String updatedById, BoardType boardType) {
        this.boardSeq = boardSeq;
        this.subJect = subJect;
        this.contents = contents;
        this.viewCnt = viewCnt;
        this.fileId = fileId;
        this.createdById = createById;
        this.updatedById = updatedById;
        this.boardType = boardType;
    }

    public void updateBoard(BoardDto.MyBoardReq dto) {
        this.subJect = dto.getSubJect();
        this.contents = dto.getContents();
    }

    public void viewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }
}
