package com.takdong.myhome.repository;

import com.takdong.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //Model 을 사용하기위해 repository를 생성해야함


    //검색할 필드명 title
    List<Board> findByTitle(String title);//인터페이스만 정의 하면 알아서 구현됌. 쿼리가 알아서 생섬된다.
    List<Board> findByTitleOrContent(String content);
}