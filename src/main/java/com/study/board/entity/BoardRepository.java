package com.study.board.entity;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//테이블의 CRUD 쿼리를 자동으로 생성해주는 레파지토리 인터페이스를 생성
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);
    //삭제되지않은 데이터만 조회하는 기능을 위해 deleteYn을 파라미터로 전달받는 메서드 추가
}
