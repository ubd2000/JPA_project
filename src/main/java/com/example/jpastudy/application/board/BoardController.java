package com.example.jpastudy.application.board;

import com.example.jpastudy.application.Response.ResponseService;
import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.application.attach.AttachFileService;
import com.example.jpastudy.support.constant.BoardType;
import com.example.jpastudy.support.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final AttachFileService attachFileService;

    /**
     * 게시판 목록 조회
     *
     * Page 클래스가 제공하는 인터페이스
     * int getNumber();                     //현재 페이지
     * int getSize();                            //페이지 크기
     * int getTotalPages();                 //전체 페이지 수
     * int getNumberOfElements();   //현재 페이지에 나올 데이터 수
     * long getTotalElements();         //전체 데이터 수
     * boolean hasPreviousPage();    //이전 페이지 여부
     * boolean isFirstPage();              //현재 페이지가 첫 페이지 인지 여부
     * boolean hasNextPage();           //다음 페이지 여부
     * boolean isLastPage();               //현재 페이지가 마지막 페이지 인지 여부
     * Pageable nextPageable();         //다음 페이지 객체, 다음 페이지가 없으면 null
     * Pageable previousPageable();   //다음 페이지 객체, 이전 페이지가 없으면 null
     * List<T> getContent();               //조회된 데이터
     * boolean hasContent();              //조회된 데이터 존재 여부
     * Sort getSort();                           //정렬정보
     *
     * @param pageable
     * @return
     */
    @ApiOperation(value = "게시판 조회", notes = "모든 게시판을 조회한다")
    @GetMapping("/boards")
    public Map<String, Object> findAllBoard(Pageable pageable, Model model) {
        List<AttachFileDto.Res> attachFileList = attachFileService.fineAllAttachFile();
        Page<BoardDto.Res> boardList = boardService.findAllBoard(pageable);
        List<Integer> pageList = boardService.getPageList(pageable.getPageNumber(), pageable.getPageSize());
        Map<String, Object> resultData = new HashMap<>();

        for (int i = 0; i < boardList.getContent().size(); i++) {
            for (int j = 0; j < attachFileList.size(); j++) {
                if (boardList.getContent().get(i).getFileId() == attachFileList.get(j).getAttachSeq()) {
                    boardList.getContent().get(i).setAttachFileName(attachFileList.get(j).getAttachFileName());
                    boardList.getContent();
                }
            }
        }
        resultData.put("boardList", boardList);
        resultData.put("pageList", pageList);
        return resultData;
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
    public SingleResult<BoardDto.Res> insertBoard(BoardDto.CreateBoard boardDto, @RequestPart MultipartFile files, Errors errors) throws IOException {

        String origFileName = files.getOriginalFilename();
        String filePath = "C:\\testJPA";
        Long fileSize = files.getSize();
        BoardDto.Res boardRes = null;
        Long fileId = null;

        if (!new File(filePath).exists()) {
            try {
                new File(filePath).mkdir();
            } catch (RuntimeException e) {

            }
        }

        if (files.isEmpty() != true) {
            files.transferTo(new File(filePath + "\\" + origFileName));

            AttachFileDto.CreateFileReq createFileReq = AttachFileDto.CreateFileReq.builder()
                    .attachFileName(origFileName)
                    .attachFileSize(fileSize)
                    .attachFileRoute(filePath + "\\" + origFileName)
                    .build();

            fileId = attachFileService.saveFile(createFileReq);
        }

        BoardDto.CreateBoard board = BoardDto.CreateBoard.builder()
                .contents(boardDto.getContents())
                .boardType(BoardType.NOTICE)
                .fileId(fileId)
                .subJect(boardDto.getSubJect())
                .viewCnt(0)
                .build();

        boardRes = boardService.insertBoard(board);

        return responseService.getSingleResult(boardRes);
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
    public SingleResult<BoardDto.Res> findBoardBySeq(@ApiParam(value = "게시판번호", required = true) @PathVariable Long boardSeq) throws Exception {
        return responseService.getSingleResult(boardService.findBoardById(boardSeq));
    }

    @GetMapping("/boards/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
        AttachFileDto.Res attachFileDto = attachFileService.findAttachFileById(fileId);
        Path path = Paths.get(attachFileDto.getAttachFileRoute());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(attachFileDto.getAttachFileName(), "UTF-8") + "\"")
                .header("Content-Type", "application/octet-stream")
                .header("Content-Transfer-Encoding", "binary")
                .body(resource);
    }

}
