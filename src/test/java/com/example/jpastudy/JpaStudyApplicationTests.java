package com.example.jpastudy;

import com.example.jpastudy.application.member.Member;
import com.example.jpastudy.application.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
class JpaStudyApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void insertMemberTest() {
        /*Member member = Member.builder()
                .name("김동민")
                .birth("19830630")
                .email("test@test.com")
                .build();*/
        //memberRepository.save(member);
    }

    @Test
    void updateMemberTest() {
        /*Member member = memberRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        member.setBirth("20000105");
        member.setName("김재경");*/
    }

}
