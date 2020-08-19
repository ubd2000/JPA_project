package com.example.jpastudy.application.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author : jkkim
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
