package com.example.jpastudy.application.attach;

import com.example.jpastudy.application.board.Board;
import lombok.*;

import java.time.LocalDateTime;

public class AttachFileDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateFileReq {
        private Long attachSeq;
        private String attachFileName;
        private Long attachFileSize;
        private String attachFileRoute;

        public AttachFile toEntity() {
            AttachFile build = AttachFile.builder()
                    .attachSeq(attachSeq)
                    .attachFileName(attachFileName)
                    .attachFileSize(attachFileSize)
                    .attachFileRoute(attachFileRoute)
                    .build();
            return build;
        }

        @Builder
        public CreateFileReq(Long attachSeq, String attachFileName, Long attachFileSize, String attachFileRoute) {
            this.attachSeq = attachSeq;
            this.attachFileName = attachFileName;
            this.attachFileSize = attachFileSize;
            this.attachFileRoute = attachFileRoute;
        }
    }

    @Getter
    public static class Res {
        private Long attachSeq;
        private String attachFileName;
        private Long attachFileSize;
        private String attachFileRoute;

        public Res(AttachFile attachFile) {
            this.attachSeq = attachFile.getAttachSeq();
            this.attachFileName = attachFile.getAttachFileName();
            this.attachFileSize = attachFile.getAttachFileSize();
            this.attachFileRoute = attachFile.getAttachFileRoute();
        }
    }
}
