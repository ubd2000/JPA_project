package com.example.jpastudy.application.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * description
 *
 * @author : jkkim
 */
@Getter
@Setter
@ToString
public class MemberDto {

    private Long memberSeq;
    private String password;
    @NotBlank
    private String name;
    private String birth;
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;


    private LocalDateTime createdAt;
    private String createdById;
    private LocalDateTime updatedAt;
    private String updatedById;
}
