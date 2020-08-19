package com.example.jpastudy.application.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author : jkkim
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /**
     * 회원 목록 조회
     *
     * @return
     */
    public List<MemberDto> findAllMember() {
        return memberRepository.findAll().stream()
                .map(member -> modelMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 회원 정보 조회
     *
     * @param memberSeq
     * @return
     */
    public MemberDto findMemberById(Long memberSeq) {
        return memberRepository.findById(memberSeq)
                .map(member -> modelMapper.map(member, MemberDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 회원 정보 등록
     *
     * @param memberDto
     * @return
     */
    public MemberDto insertMember(MemberDto memberDto) {
        memberRepository.save(modelMapper.map(memberDto, Member.class));
        return memberDto;
    }

    /**
     * 회원 정보 수정
     *
     * @param memberDto
     * @return
     */
    public MemberDto updateMember(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getMemberSeq())
                .orElseThrow(EntityNotFoundException::new);
        member.setBirth(memberDto.getBirth());
        member.setName(memberDto.getName());
        return memberDto;
    }

}
