package com.study.board;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepository; //객체를 주입받음.

    @Test
    void save() {

        Board params = Board.builder()
                .title("두번째 게시글 제목")
                .content("두번째 게시글 내용")
                .writer("두번째 작성자")
                .hits(0)
                .deleteYn('N')
                .build();

        boardRepository.save(params);
        //Board entity = new Board("테스트 게시글 제목", "테스트 게시글 내용", "테스트 작성자", 0, 'N'); 과 동일!!


        Board entity = boardRepository.findById((long) 2).get();
        assertThat(entity.getTitle()).isEqualTo("두번째 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("두번째 게시글 내용");
        assertThat(entity.getWriter()).isEqualTo("두번째 작성자");
    }

    @Test
    void findAll() {
        long boardsCount = boardRepository.count(); //따로 변수만들어서 전체 게시글 수 저장

        List<Board> boards = boardRepository.findAll(); //전체 게시글 리스트 조회
    }

    @Test
    void delete() {

        Board entity = boardRepository.findById((long)1).get(); //게시글 조회
        //findById()의 리턴타입은 Optional<T> 라는 클래스


        boardRepository.delete(entity);

    }

}
