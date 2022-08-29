package com.study.board.controller;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.model.BoardService;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") //Rest API 기반 비동기 방식
// 페이지를 처리하는 Controller와 API를 처리하는 Controller를 따로 구성
//해당 클래스에서는 API를 처리하며 전역에서 공통된 예외처리를 적용하는 방법임
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params) {
        return boardService.save(params);
    }

    @GetMapping("/boards")
    public List<BoardResponseDto> findAll() {
        return boardService.findAll();
    }

    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }


//    @GetMapping("/test")
//    public String test(){
//        //throw new RuntimeException("Holy! Exception...!");
//        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
//        // 예외에 대한 응답이 어떤 흐름으로 처리되는지 확인차원에서 메서드 실행 시, 강제로 CustomException 발생시키게 변경.
//    }


}
