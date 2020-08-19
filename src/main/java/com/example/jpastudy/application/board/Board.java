package com.example.jpastudy.application.board;

import com.example.jpastudy.application.attach.AttachFile;
import com.example.jpastudy.support.constant.BoardType;
import com.example.jpastudy.support.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * description
 *
 * @author : jkkim
 */
@Entity
@Table(name = "BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_seq")
    private Long boardSeq;
    /** 게시판 유형 */
    private BoardType boardType;
    //제목
    private String subJect;
    //내용
    private String contents;
    //조회수
    private Integer viewCnt;
    //첨부파일 Entity
    @OneToOne(fetch=FetchType.LAZY, mappedBy="board", cascade = CascadeType.ALL)
    private AttachFile attachFile;

    @Builder
    public Board(Long boardSeq, BoardType boardType, String subJect, String contents, Integer viewCnt) {
        this.boardSeq = boardSeq;
        this.boardType = boardType;
        this.subJect = subJect;
        this.contents = contents;
        this.viewCnt = viewCnt;
    }

    public void updateBoard(BoardDto.MyBoardReq dto) {
        this.subJect = dto.getSubJect();
        this.contents = dto.getContents();
    }
}
