package com.example.jpastudy.application.board;

import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.application.attach.AttachFileRepository;
import com.example.jpastudy.application.attach.AttachFileService;
import com.example.jpastudy.support.exception.CBoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author : jkkim
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final AttachFileService attachFileService;
    private final BoardRepository boardRepository;
    private final AttachFileRepository attachFileRepository;
    private final ModelMapper modelMapper;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private int PAGE_POST_COUNT = 3;       // 한 페이지에 존재하는 게시글 수


    /**
     * 게시판 목록 조회
     *
     * @param pageable
     * @return
     */
    public Page<BoardDto.Res> findAllBoard(Pageable pageable) {

        return boardRepository.findAll(pageable).map(BoardDto.Res::new);
    }

    /**
     * 게시판 정보 조회
     *
     * @param boardSeq
     * @return
     */
    public BoardDto.Res findBoardById(Long boardSeq) {
        Board board = boardRepository.findById(boardSeq).orElseThrow(CBoardNotFoundException::new);
        board.viewCnt(board.getViewCnt() + 1);
        BoardDto.Res boardRes = new BoardDto.Res(board);

        AttachFileDto.Res attachFileDtoRes = attachFileService.findAttachFileById(boardRes.getFileId());

        if (attachFileDtoRes != null) {
            boardRes.setAttachFileDtoRes(attachFileDtoRes);
        }

        return boardRes;
    }

    /**
     * 게시판 등록
     *
     * @param boardDto
     * @return
     */
    @Transactional
    public BoardDto.Res insertBoard(BoardDto.CreateBoard boardDto) {
        Board board = boardRepository.save(boardDto.toEntity());

        return new BoardDto.Res(board);
    }

    /**
     * 게시판 수정
     *
     * @param boardDto
     * @return
     */
    public Board updateBoard(Long id, BoardDto boardDto) {
//        Board board = boardRepository.findById(boardDto.getBoardSeq())
//                .orElseThrow(EntityNotFoundException::new);
//        board.setSubJect(boardDto.getSubJect());
//        board.setContents(boardDto.getContents());
//        return boardDto;
//        final Board board = boardRepository.findById(id);
//        board.updateBoard(boardDto);
//        return board;
        return null;
    }

    /**
     * 게시판 삭제
     *
     * @param boardSeq
     */
    public void deleteBoard(Long boardSeq) {
        boardRepository.deleteById(boardSeq);
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public List<Integer> getPageList(Integer curPageNum, Integer curPageSize) {
        List<Integer> pageList = new ArrayList<>();

        PAGE_POST_COUNT = curPageSize;

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer startPageNum = 0;
        Integer lastPageNum = 0;
        Integer tempPageNum = 0;

        if (curPageNum % BLOCK_PAGE_NUM_COUNT == 0) {
            startPageNum = curPageNum + 1;
            lastPageNum = curPageNum + BLOCK_PAGE_NUM_COUNT;
        } else {
            tempPageNum = curPageNum / BLOCK_PAGE_NUM_COUNT;
            lastPageNum = (tempPageNum + 1) * BLOCK_PAGE_NUM_COUNT;
            startPageNum = lastPageNum - (BLOCK_PAGE_NUM_COUNT - 1);
        }

        lastPageNum = (totalLastPageNum < lastPageNum) ? totalLastPageNum : lastPageNum;

        for (int val = startPageNum, idx = 0; val <= lastPageNum; val++, idx++) {
            pageList.add(idx,val);
        }

        return pageList;
    }


}
