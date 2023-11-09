package com.example.prj1be1109.Controller;

import com.example.prj1be1109.domain.Board;
import com.example.prj1be1109.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//1109 11시 26분
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board) {
//        System.out.println("board = "+ board);
//            service.save(board);
        System.out.println(board);
        if (!service.validate(board)){
            return ResponseEntity.badRequest().build();
        }
        if (service.save(board)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("list")
    public List<Board> list(){
        return service.list();
    }
}

