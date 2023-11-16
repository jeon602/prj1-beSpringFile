package com.example.prj1be20231109.service;

import com.example.prj1be20231109.domain.Like;
import com.example.prj1be20231109.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper mapper;
    public void update(Like like){
        //처음 좋아요 누를 때 insert
        // 다시 누르면 delete
        // 처음 눌렀는지 다시 눌렀는지 알아야 함.
//        0이면 원래 없었던 것,
        int count = 0;
        if(mapper.delete(like)==0){
            count = mapper.insert(like);
        }
        }
        //
    }


