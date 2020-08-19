package com.example.jpastudy.application.board;

import com.example.jpastudy.application.attach.AttachFile;
import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.support.constant.BoardType;
import lombok.*;

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
        private String subJect;
        private String contents;
        private String createdById;
        private AttachFile attachFile;

        @Builder
        public CreateBoard(String subJect, String contents, String createdById, AttachFile attachFile) {
            this.subJect = subJect;
            this.contents = contents;
            this.createdById = createdById;
            this.attachFile = attachFile;
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
    public static class Res {

        private String subJect;
        private String contents;
        private AttachFile attachFile;

        public Res(Board board) {
            this.subJect = board.getSubJect();
            this.contents = board.getContents();
            this.attachFile = board.getAttachFile();
        }
    }

}
