package com.study.board.model;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 클래스 내 final로 선언된 모든 멤버에 대한 생성자를 만들어줌.
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // JPA의 서비스 클래스에서 필수로 사용되어야하는 어노테이션
    // 각각 실행(begin), 종료(commit), 예외(rollback)를 자동으로 처리해줌.
    public Long save(final BoardRequestDto params) { //새로운 게시글 생성하는 메서드
        Board entity = boardRepository.save(params.toEntity());
        //해당 메서드 실행 순서
        //1. BoardRequestDto 형태의 Params 객체를 매개값로 받아옴.
        //2. 데이터베이스 자체인 entity = boardRepository의 save() 메서드를 실행
        //3. save() 메서드의 매개값으로 params 객체를 toEntity 메서드(빌더)를 이용

        return entity.getId();
    }
    //@TransActional 이 선언된 메서드는 메서드의 로직이 정상적으로 종료되었을 때
    //실행된 SQL 쿼리에 대한 COMMIT을 수행하고, 해당 메서드에 대한 트랜잭션을 종료함.
    //POST 방식으로 매핑된 URL에 파라미터값을 던져 테스트해볼 것.

    public List<BoardResponseDto> findAll() { //Request Method가 GET으로 매핑되어있으니 테스트해볼것.
        Sort sort = Sort.by(Direction.DESC, "id", "CreatedDate");
        //Sort 갹채 생성 (ORDER BY DESC, created_date DESC를 의미)
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        //list에 게시글 Entity가 담겨있고, 각각의 Entity를 BoardResponseDto 타입으로 변경(생성)해서
        //리턴해줌. 문법 솔직히 이해가 안감... ㅠㅠ
        //Stream API를 사용한 방법인데 이걸 풀면 아래 메서드가 됨.
    }

//    public List<BoardResponseDto> findAll_noAPI(){
//        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
//        List<Board> list = boardRepository.findAll(sort);
//
//        List<BoardResponseDto> boardList = new ArrayList<>();
//
//        for(Board entity : list) {
//            boardList.add(new BoardResponseDto(entity));
//        }
//
//        return boardList;
//    }

    @Transactional //안써서 업데이트 때, 오류났었음. 정신차릴것
    public Long update(final Long id, final BoardRequestDto params) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        //orElseThrow()는 Optional 클래스에 포함된 메서드로, Entity 조회와 예외처리를 한 줄로 처리할 수 있음.
        //아래 풀어썼을때 코드 참조

        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        //entity.update 메서드에는 update 쿼리를 실행하는 로직이 없음.
        //but, 해당 메서드 실행이 종료되면 update 쿼리가 자동으로 실행됨.
        //이는 JPA의 영속성 컨텍스트라는 개념이 있는데 영속성 컨텍스트에 대해선 노션에 저

        //JPA의 '엔티티 매니저'는 Entity 생성 또는 조회하는 시점에 영속성 컨텍스트에 Entity를 보관 및 관리함.
        //영속성 컨텍스트에 포함된 Entity 객체의 값이 변경되면 트랜잭션이 종료(commit)되는 시점에
        //update 쿼리를 실행하게되는데 이렇게 자동으로 쿼리가 실행되는 개념을 더티 체킹(Dirty Checking)이라 함.
        //쉽게 말해 영속성 컨텍스트에 의해 더티 체킹이 가능해진다. 로 이해하면 됨.
        return id;
    }

//    @Transactional
//    public Long update_no_orElseThrow(final Long id, final BoardRequestDto params) {
//
//        Board entity = boardRepository.findById(id).orElse(null);
//
//        if (entity == null) {
//            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
//        }
//
//        entity.update(params.getTitle(), params.getContent(), params.getWriter());
//        return id;
//    }


    @Transactional
    public Long delete(final Long id) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();

        return id;
    }

    @Transactional
    public BoardResponseDto findById(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        entity.increaseHits();
        return new BoardResponseDto(entity);

    }
}
