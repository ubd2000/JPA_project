package com.example.jpastudy.application.attach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author : kdm
 */
@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {

}
