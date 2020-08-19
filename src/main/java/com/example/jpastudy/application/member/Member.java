package com.example.jpastudy.application.member;

import com.example.jpastudy.support.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * description
 *
 * @author : jkkim
 */
@Entity
@Table(name = "MEMBER")
@Setter
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberSeq;
    private String password;
    private String name;
    private String birth;
    private String email;

}
