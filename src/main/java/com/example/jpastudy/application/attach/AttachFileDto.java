package com.example.jpastudy.application.attach;

import com.example.jpastudy.application.board.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class AttachFileDto {

    private Long boardSeq;
    private String attachFileName;
    private String attachFileSize;
    private String attachFileRoute;
    private LocalDateTime createdAt;
    private String createdById;
    private Board board;
}
