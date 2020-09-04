package com.example.jpastudy.application.attach;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachFileService {

    final private AttachFileRepository attachFileRepository;

    @Transactional
    public Long saveFile(AttachFileDto.CreateFileReq createFileReq) {
        return attachFileRepository.save(createFileReq.toEntity()).getAttachSeq();
    }

    public List<AttachFileDto.Res> fineAllAttachFile() {
        List<AttachFile> list = attachFileRepository.findAll();
        List<AttachFileDto.Res> listResult = new ArrayList<>();

        for(AttachFile attachFile : list) {
            listResult.add(new AttachFileDto.Res(attachFile));
        }
        return listResult;
    }

    public AttachFileDto.Res findAttachFileById(Long fileId) {
        AttachFileDto.Res attachFileDtoRes = null;
        if(fileId != null) {
            attachFileDtoRes = new AttachFileDto.Res(attachFileRepository.findById(fileId).get());
        }
        return attachFileDtoRes;
    }
}
