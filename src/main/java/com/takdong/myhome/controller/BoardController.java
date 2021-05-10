package com.takdong.myhome.controller;


import com.takdong.myhome.model.Board;
import com.takdong.myhome.repository.BoardRepository;
import com.takdong.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    //보드레포지토리를이용해 테이블의 데이터를 가져오기위함
    @Autowired
    private BoardRepository boardRepository;//repostitory를 이용해서 테이블의 데이터를 가져옴
    @Autowired
    private BoardValidator boardValidator;
   @GetMapping("/list")
    public String list(Model model) {//게시판호출할때 데이터값을 넘겨주고싶으면 파라미터로 model을 주면됨

       List<Board> boards = boardRepository.findAll();//findall 하면db값을 다 가져올수있음.trl spacebar 눌러보면 list <board> type 이다.
       model.addAttribute("boards", boards);//model 에 담긴 데이터들은  타임리프에서 사용가능
       return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}