package com.example.jpastudy.application.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author : jkkim
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
