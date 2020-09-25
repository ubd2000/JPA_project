package com.example.jpastudy.application.board;

import com.example.jpastudy.application.attach.AttachFile;
import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.support.constant.BoardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * description
 *
 * @author : jkkim
 */
public class BoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateBoard {
        private Long boardSeq;
        private BoardType boardType;
        private String subJect;
        private String contents;
        private Long fileId;
        private Integer viewCnt;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;

        @Builder
        public CreateBoard(Long boardSeq, BoardType boardType, String subJect, String contents, Long fileId, Integer viewCnt) {
            this.boardSeq = boardSeq;
            this.boardType = boardType;
            this.subJect = subJect;
            this.contents = contents;
            this.fileId = fileId;
            this.viewCnt = viewCnt;
        }

        public Board toEntity() {
            Board build = Board.builder()
                    .boardSeq(boardSeq)
                    .boardType(boardType)
                    .contents(contents)
                    .subJect(subJect)
                    .fileId(fileId)
                    .viewCnt(viewCnt)
                    .build();
            return build;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyBoardReq {
        private String subJect;
        private String contents;

        @Builder
        public MyBoardReq(String subJect, String contents) {
            this.subJect = subJect;
            this.contents = contents;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BoardViews {
        private Integer viewCnt;

        @Builder
        public BoardViews(Integer viewCnt) {
            this.viewCnt = viewCnt;
        }
    }

    @Getter
    @Setter
    public static class Res {

        private Long boardSeq;
        private String subJect;
        private String contents;
        private Integer viewCnt;
        private Long fileId;
        private String attachFileName;
        private AttachFileDto.Res attachFileDtoRes;
        private Integer[] pageList;
        //아무런 가공이 없는 LocalDateTime 의 응답 포맷은 배열이 되어 JsonFormat을 사용하기 위해 Jackson 라이브러리 추가
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        private String createdById;

        public Res(Board board) {
            this.boardSeq = board.getBoardSeq();
            this.subJect = board.getSubJect();
            this.contents = board.getContents();
            this.viewCnt = board.getViewCnt();
            this.fileId = board.getFileId();
            this.createdAt = board.getCreatedDate();
            this.createdById = board.getCreatedById();
        }
    }

}
