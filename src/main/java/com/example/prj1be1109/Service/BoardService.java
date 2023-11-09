package com.example.prj1be1109.Service;


import com.example.prj1be1109.domain.Board;
import com.example.prj1be1109.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public void save(Board board) {
        mapper.insert(board);
    }
}
