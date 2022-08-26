package com.study.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    //여기까지 작성했다면 게시글 CRUD 테스트 준비 완료
    //작성 순서 : 애플리케이션 프로퍼티, 컨픽, 엔티티, 레파지토리 인터페이스
}
