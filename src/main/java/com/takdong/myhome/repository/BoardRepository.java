package com.takdong.myhome.repository;

import com.takdong.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //Model 을 사용하기위해 repository를 생성해야함
}