package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    @GetMapping("/list")
    public String openBoardList() {
        return "board/list";
    }

    @GetMapping("/write")
    public String openBoardWrite() {
        return "board/write";
    }
}
