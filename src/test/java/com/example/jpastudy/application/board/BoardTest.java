package com.example.jpastudy.application.board;

import com.example.jpastudy.support.constant.BoardType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description
 *
 * @author : jkkim
 */
@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoardTest() {
//        Board board = new Board();
//        board.setBoardType(BoardType.NOTICE);
//        board.setSubJect("제목1");
//        board.setContents("내용1");
//        board.setViewCnt(0);
//        boardRepository.save(board);
    }

}
