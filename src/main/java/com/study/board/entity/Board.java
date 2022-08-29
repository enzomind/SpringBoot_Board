package com.study.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
//엔티티 클래스에서 @Setter 가 존재해서는 안됨.
//엔티티 클래스가 테이블 그 자체이기때문에 각각의 멤버 변수는 해당 테이블의 컬럼이 되고
//컬럼에 대한 Setter를 무작정 생성하는 경우 객체의 값이 어느 시점에 변경되었는지 알 수 없음.
//해결책으로 BoardResponseDto를 생성해서 복사본(객체)에 세팅
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 생성해줌
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hits;
    private char deleteYn; // 삭제 여부
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate;

    @Builder
    public Board(String title, String content, String writer, int hits, char deleteYn) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.deleteYn = deleteYn;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }

}
