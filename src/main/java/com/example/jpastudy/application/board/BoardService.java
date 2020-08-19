package com.example.jpastudy.application.board;

import com.example.jpastudy.application.attach.AttachFile;
import com.example.jpastudy.application.attach.AttachFileDto;
import com.example.jpastudy.application.attach.AttachFileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

/**
 * description
 *
 * @author : jkkim
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AttachFileRepository attachFileRepository;
    private final ModelMapper modelMapper;


    /**
     * 게시판 목록 조회
     *
     * @param pageable
     * @return
     */
    public Page<BoardDto> findAllBoard(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(board -> modelMapper.map(board, BoardDto.class));
    }

    /**
     * 게시판 정보 조회
     *
     * @param boardSeq
     * @return
     */
    public BoardDto findBoardById(Long boardSeq) {
        return boardRepository.findById(boardSeq)
                .map(board -> modelMapper.map(board, BoardDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 게시판 등록
     *
     * @param boardDto
     * @return
     */
    public BoardDto insertBoard(BoardDto boardDto, MultipartFile files) {
        Board board = modelMapper.map(boardDto, Board.class);
        AttachFileDto attachFileDto = new AttachFileDto();
        if(!files.isEmpty()) {

            String baseDir = "C:\\download\\";
            String filePath = baseDir + files.getOriginalFilename();

            attachFileDto.setBoardSeq(board.getBoardSeq());
            attachFileDto.setAttachFileName(files.getOriginalFilename());
            attachFileDto.setAttachFileSize(Long.toString(files.getSize()));
            attachFileDto.setAttachFileRoute(filePath);
        }
        AttachFile attachFile = modelMapper.map(attachFileDto, AttachFile.class);
//        board.setAttachFile(attachFile);
        attachFile.setBoard(board);



//        if(attachFileDto != null ) {
//            attachFileDto.setBoardSeq(board.getBoardSeq());
//            AttachFile attachFile = modelMapper.map(attachFileDto, AttachFile.class);
//            board.setAttachFileEntity(attachFile);
//        }
        boardRepository.save(board);
        return boardDto;
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

}
