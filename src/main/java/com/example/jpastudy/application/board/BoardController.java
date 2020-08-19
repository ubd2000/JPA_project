package com.example.jpastudy.application.board;

import com.example.jpastudy.application.Response.ResponseService;
import com.example.jpastudy.application.attach.AttachFile;
import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.support.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * description
 *
 * @author : jkkim
 */
@Api(tags = {"1. Board"})
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    /**
     * 게시판 목록 조회
     *
     * @param pageable
     * @return
     */
    @ApiOperation(value = "게시판 조회", notes = "모든 게시판을 조회한다")
    @GetMapping("/boards")
    public Page<BoardDto> findAllBoard(Pageable pageable) {
        return boardService.findAllBoard(pageable);
    }

    /**
     * 게시판 상세 조회
     *
     * @param boardSeq
     * @return
     */
//    @GetMapping("/boards/{boardSeq}")
//    public BoardDto findBoardById(@PathVariable @NotNull Long boardSeq) {
//        return boardService.findBoardById(boardSeq);
//    }


    /**
     * 게시판 등록
     *
     * @param boardDto
     * @param errors
     * @return
     */
    @PostMapping("/boards/write")
    public ResponseEntity insertBoard(BoardDto boardDto, @RequestPart MultipartFile files, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
         boardService.insertBoard(boardDto, files);
        return ResponseEntity.ok().build();
    }


    /**
     * 게시판 수정
     *
     * @param boardDto
     * @param errors
     * @return
     */
    public ResponseEntity updateBoard(@RequestBody @Valid BoardDto boardDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
//        boardService.updateBoard(boardDto);
        return ResponseEntity.ok().build();
    }


    /**
     * 게시판 삭제
     *
     * @param boardSeq
     */
    @DeleteMapping("/boards/{boardSeq}")
    public void deleteBoard(@PathVariable @NotNull Long boardSeq) {
        boardService.deleteBoard(boardSeq);
    }

    @GetMapping(value = "/boards/{boardSeq}")
    public SingleResult<BoardDto> findBoardBySeq(@ApiParam(value = "게시판번호", required = true) @PathVariable Long boardSeq) {
        return responseService.getSingleResult(boardService.findBoardById(boardSeq));
    }

}
