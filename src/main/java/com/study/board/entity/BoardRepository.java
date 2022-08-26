package com.study.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

//테이블의 CRUD 쿼리를 자동으로 생성해주는 레파지토리 인터페이스를 생성
public interface BoardRepository extends JpaRepository<Board, Long> {


}
