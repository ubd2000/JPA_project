package com.example.jpastudy.application.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description
 *
 * @author : jkkim
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원 목록 조회
     *
     * @return
     */
    @GetMapping("/members")
    public List<MemberDto> findAllMember() {
        return memberService.findAllMember();
    }


    /**
     * 회원 정보 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/members/{id}")
    public MemberDto findMemberById(@PathVariable @NotNull Long id) {
        return memberService.findMemberById(id);
    }


    /**
     * 회원 정보 등록
     *
     * @param memberDto
     * @return
     */
    @PostMapping("/members")
    public ResponseEntity createMember(@RequestBody @Valid MemberDto memberDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        memberService.insertMember(memberDto);
        return ResponseEntity.ok().build();
    }


    /**
     * 회원 정보 수정
     *
     * @param memberDto
     */
    @PatchMapping("/members")
    public void updateMember(@RequestBody @Valid MemberDto memberDto) {
        memberService.updateMember(memberDto);
    }

}
