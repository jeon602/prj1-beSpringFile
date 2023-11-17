package com.example.prj1be20231109.controller;

import com.example.prj1be20231109.domain.Like;
import com.example.prj1be20231109.domain.Member;
import com.example.prj1be20231109.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService service;

    //
    @PostMapping
    public ResponseEntity<Map<String, Object>> like(@RequestBody Like like,
                                                    @SessionAttribute(value = "login", required = false) Member login) {
        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(service.update(like, login));
    }
}

//하트를 눌렀을 때 좋아요를 한건지 취소를 한건지 알아야 함.
//        좋아요 개수 정보도 있어야 함
//        {like : true,
//        countlike: 5 ----> 좋아요 개수는? 자바빈 or map으로 넘김}