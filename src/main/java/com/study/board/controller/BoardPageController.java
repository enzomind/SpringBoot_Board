package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    @GetMapping("/list")
    public String openBoardList() {
        return "board/list";
    }

    @GetMapping("/write")
    public String openBoardWrite(@RequestParam(required = false) final Long id, Model model){
        model.addAttribute("id", id);

        return "board/write";
    }


    @GetMapping("/view/{id}")
    public String openBoardView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "board/view";
    }
}
